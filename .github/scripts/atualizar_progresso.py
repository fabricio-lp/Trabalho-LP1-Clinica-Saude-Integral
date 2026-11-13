#!/usr/bin/env python3
"""Atualiza README.md e gera SVG a partir dos checkboxes do Checklist."""
from pathlib import Path
import re

ROOT = Path(__file__).resolve().parents[2]
MD = ROOT / 'README.md'
SVG = ROOT / 'assets' / 'progresso.svg'
text = MD.read_text(encoding='utf-8')
match = re.search(r'^## Checklist\s*$([\s\S]*?)(?=^## |\Z)', text, re.M)
if not match:
    raise SystemExit('Seção ## Checklist não encontrada')
tasks = re.findall(r'^\s*[-*]\s+\[([ xX])\]\s+', match.group(1), re.M)
if not tasks:
    raise SystemExit('Nenhuma tarefa no checklist')
done, total = sum(x.lower() == 'x' for x in tasks), len(tasks)
pct = round(done * 100 / total)
start, end = '<!-- PROGRESSO-INICIO -->', '<!-- PROGRESSO-FIM -->'
if start not in text or end not in text:
    raise SystemExit('Marcadores de progresso não encontrados')
line = f'{start}\n**Progresso: {pct}%** · {done}/{total} tarefas concluídas\n{end}'
text = re.sub(re.escape(start)+r'.*?'+re.escape(end), lambda _: line, text, count=1, flags=re.S)
MD.write_text(text, encoding='utf-8')

# Visual editorial, sem gradientes, sombras ou cartões.
left, right = 27, 793
gap = 7
step = (right - left - (total - 1) * gap) / total
segments = []
for i in range(total):
    x = left + i * (step + gap)
    fill = '#D8563E' if i < done else '#DEDED8'
    segments.append(f'<rect x="{x:.2f}" y="142" width="{step:.2f}" height="12" fill="{fill}"/>')
svg = f'''<svg xmlns="http://www.w3.org/2000/svg" width="820" height="179" viewBox="0 0 820 179" role="img" aria-label="Progresso: {pct} por cento, {done} de {total} tarefas concluídas">
  <title>Progresso da implementação: {pct}%</title>
  <desc>{done} de {total} etapas concluídas na Unidade II de LP1.</desc>
  <rect width="820" height="179" fill="#F7F7F3"/>
  <rect x="27" y="23" width="15" height="3" fill="#D8563E"/>
  <text x="53" y="29" font-family="Arial,Helvetica,sans-serif" font-size="12" font-weight="700" letter-spacing="1.0" fill="#252825">LP1 / UNIDADE II</text>
  <text x="793" y="29" text-anchor="end" font-family="Arial,Helvetica,sans-serif" font-size="11" font-weight="500" letter-spacing="0.6" fill="#6C706C">CLÍNICA SAÚDE INTEGRAL</text>
  <path d="M27 43H793" stroke="#CFD0C9" stroke-width="1"/>
  <text x="27" y="110" font-family="Arial,Helvetica,sans-serif" font-size="62" font-weight="700" letter-spacing="-3" fill="#252825">{done:02d}</text>
  <text x="112" y="109" font-family="Arial,Helvetica,sans-serif" font-size="24" font-weight="400" fill="#777A74">/ {total:02d}</text>
  <text x="793" y="110" text-anchor="end" font-family="Arial,Helvetica,sans-serif" font-size="62" font-weight="700" letter-spacing="-3" fill="#252825">{pct}%</text>
  <text x="29" y="132" font-family="Arial,Helvetica,sans-serif" font-size="10.5" font-weight="700" letter-spacing="1.15" fill="#777A74">ETAPAS CONCLUÍDAS</text>
  {''.join(segments)}
  <path d="M27 166H793" stroke="#E2E2DC" stroke-width="1"/>
</svg>
'''
SVG.parent.mkdir(parents=True, exist_ok=True)
SVG.write_text(svg, encoding='utf-8')
print(f'Progresso: {done}/{total} ({pct}%)')
