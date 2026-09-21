# 07 – Planejamento do grupo

> As datas e os nomes abaixo são **modelos**. O grupo deve ajustá-los ao prazo real informado pelo professor.

## 1. Papéis sugeridos

O tamanho do grupo não foi informado. Adaptar: um integrante pode acumular papéis.

| Papel | Responsabilidade principal | Responsável |
|---|---|---|
| Líder / integração | Organizar tarefas, revisar PRs, montar a entrega | *(preencher)* |
| Lógica do jogo | `domain/` (`MemoryGame`, `StarRating`) e testes unitários | *(preencher)* |
| Interface | Layouts XML, cores, temas, animações, acessibilidade | *(preencher)* |
| Dados e telas secundárias | `data/`, Recordes, Configurações, Resultado | *(preencher)* |
| Documentação e QA | Manter `docs/`, executar o plano de testes, preparar a apresentação | *(preencher)* |

## 2. Divisão de tarefas por fase

| Fase | Entregas | Documento de referência |
|---|---|---|
| 1. Fundação | Projeto criado no Android Studio, repositório Git, tema visual, navegação entre telas vazias | [03](03-design-e-usabilidade.md), [04](04-arquitetura-e-implementacao.md) |
| 2. Núcleo do jogo | `MemoryGame` + testes unitários; tela de Jogo com tabuleiro | [04](04-arquitetura-e-implementacao.md) §3 |
| 3. Experiência | Animações, cronômetro, pausa, reiniciar, diálogos, rotação | [03](03-design-e-usabilidade.md) §6 |
| 4. Persistência e extras | Recordes, Configurações, Resultado, Compartilhar | [04](04-arquitetura-e-implementacao.md) §5 |
| 5. Qualidade | Testes manuais, correções, revisão de código, acessibilidade, citações | [05](05-plano-de-testes.md), [06](06-padroes-de-codigo-e-originalidade.md) |
| 6. Entrega | Documentação final, APK, apresentação, envio no Teams | [09](09-checklist-de-entrega.md) |

## 3. Cronograma (modelo em semanas)

| Semana | Fase | Marco |
|---|---|---|
| 1 | 1 | Projeto rodando com navegação básica |
| 2 | 2 | Partida jogável de ponta a ponta (sem enfeites) |
| 3 | 3 e 4 | Todas as funcionalidades M e S implementadas |
| 4 | 5 e 6 | Testes concluídos, documentação final, entrega |

## 4. Gestão de riscos

| Risco | Probabilidade | Impacto | Mitigação |
|---|---|---|---|
| Prazo curto | Média | Alto | Entregar primeiro as prioridades **M**; **C** só se sobrar tempo |
| Conflitos de Git | Média | Médio | Branches curtas, integrar com frequência, dividir por arquivo |
| Emojis com aparência diferente entre aparelhos | Média | Baixo | Testar em 2 versões de Android; plano B: vetores próprios |
| Bug na rotação de tela perdendo estado | Média | Alto | Manter o estado no `ViewModel`; teste TM19 cedo |
| Integrante com pouca participação | Baixa | Alto | Tarefas visíveis no quadro; histórico do Git; alinhar cedo com o professor |
| Suspeita de plágio | Baixa | Crítico | Seguir [06](06-padroes-de-codigo-e-originalidade.md) §4; citar tudo; cada um explica o próprio código |
| Erro de versão do Gradle/SDK entre máquinas | Média | Médio | Fixar versões no `build.gradle`; usar a mesma versão do Android Studio |
| Cronômetro e atraso gerando estado inconsistente | Média | Médio | Testes com `kotlinx-coroutines-test` (TU28–TU31) |

## 5. Quadro de tarefas (modelo)

| ID | Tarefa | Requisito | Responsável | Estimativa | Status |
|---|---|---|---|---|---|
| T01 | Criar projeto e repositório | — | | 1 h | ☐ |
| T02 | Implementar `Card`, `Difficulty`, `GameTheme` | RF01–RF03 | | 1 h | ☐ |
| T03 | Implementar `MemoryGame` | RF03–RF06 | | 3 h | ☐ |
| T04 | Testes unitários do `MemoryGame` (TU01–TU12) | RNF10 | | 2 h | ☐ |
| T05 | `StarRating` + testes (TU13–TU19) | RN05 | | 1 h | ☐ |
| T06 | Layout do Menu e `MainActivity` | RF01, RF02 | | 3 h | ☐ |
| T07 | Layout do Jogo, `CardAdapter`, `GameViewModel` | RF04–RF08 | | 6 h | ☐ |
| T08 | Animações (giro, pulso, erro) | RF04, RF05 | | 3 h | ☐ |
| T09 | Pausar, reiniciar, voltar com confirmação | RF09, RF10, RF18 | | 2 h | ☐ |
| T10 | `ResultActivity` e compartilhar | RF11, RF12 | | 3 h | ☐ |
| T11 | `ScoreRepository` + testes (TU20–TU27) | RF13 | | 3 h | ☐ |
| T12 | `RecordsActivity` e `RecordsAdapter` | RF14, RF15 | | 3 h | ☐ |
| T13 | Configurações e modo escuro | RF16 | | 2 h | ☐ |
| T14 | Adaptação à rotação e a telas grandes | RF17, RNF07 | | 3 h | ☐ |
| T15 | Acessibilidade e contraste | RNF03–RNF05 | | 2 h | ☐ |
| T16 | Ícone do app | — | | 1 h | ☐ |
| T17 | Testes manuais e correções | Todos | | 4 h | ☐ |
| T18 | Revisão final de código, KDoc e citações | RNF12 | | 3 h | ☐ |
| T19 | Fechamento da documentação, prints e APK | — | | 2 h | ☐ |

*(As estimativas são apenas referência inicial.)*

## 6. Registro de reuniões (modelo)

| Data | Participantes | Decisões | Pendências |
|---|---|---|---|
| | | | |

## 7. Contribuição individual (preencher ao final)

| Integrante | Principais contribuições | Arquivos/telas |
|---|---|---|
| | | |
