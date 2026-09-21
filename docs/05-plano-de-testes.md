# 05 – Plano de testes

O critério de **Funcionalidade (30%)** exige que tudo o que foi proposto funcione sem erros. Este plano garante e comprova isso.

## 1. Estratégia

| Nível | O que testa | Ferramenta | Quando |
|---|---|---|---|
| Unitário | Regras do jogo, estrelas, ranking, leitura/escrita de recordes | JUnit 4 + `kotlinx-coroutines-test` | A cada mudança na lógica |
| Instrumentado (opcional) | Fluxo de telas (Menu → Jogo → Resultado) | Espresso | Antes da entrega |
| Manual | Aparência, animações, rotação, acessibilidade, aparelhos diferentes | Roteiro da seção 3 | Antes da entrega |

## 2. Testes unitários planejados

### 2.1 `MemoryGame`

| ID | Cenário | Resultado esperado |
|---|---|---|
| TU01 | Criar jogo Fácil | 12 cartas; 6 símbolos distintos, cada um 2 vezes |
| TU02 | Criar jogo Médio e Difícil | 16 e 24 cartas |
| TU03 | Criar dois jogos seguidos | Ordem das cartas difere (com semente fixa, é reproduzível) |
| TU04 | Virar 1ª carta | Estado `REVEALED`; jogadas continuam 0 |
| TU05 | Virar 2ª carta igual | Ambas `MATCHED`; jogadas = 1; retorna `MATCH` |
| TU06 | Virar 2ª carta diferente | Retorna `MISMATCH`; jogadas = 1; jogo bloqueado |
| TU07 | Virar carta durante o bloqueio | Ignorado; nada muda |
| TU08 | `resolveMismatch()` | Cartas voltam a `HIDDEN`; jogo desbloqueado |
| TU09 | Virar a mesma carta duas vezes | 2º toque ignorado; jogada não conta |
| TU10 | Virar carta já `MATCHED` | Ignorado |
| TU11 | Encontrar o último par | Retorna `FINISHED`; `isFinished()` = true |
| TU12 | Reiniciar | Zera jogadas, todas as cartas `HIDDEN`, novo embaralhamento |

### 2.2 `StarRating` (RN05)

| ID | Pares | Jogadas | Esperado |
|---|---|---|---|
| TU13 | 8 | 8 (mínimo possível) | 3★ |
| TU14 | 8 | 12 (limite de 1,5·n) | 3★ |
| TU15 | 8 | 13 | 2★ |
| TU16 | 8 | 20 (limite de 2,5·n) | 2★ |
| TU17 | 8 | 21 | 1★ |
| TU18 | 6 | 9 | 3★ |
| TU19 | 6 | 10 | 2★ |

### 2.3 `ScoreRepository` (com `SharedPreferences` de teste ou fake)

| ID | Cenário | Resultado esperado |
|---|---|---|
| TU20 | Salvar 1º resultado | Retorna `true` (novo recorde); lista tem 1 item |
| TU21 | Salvar resultado melhor | Vai para o topo da lista |
| TU22 | Salvar dois com mesmas jogadas | O de menor tempo fica na frente (RN06) |
| TU23 | Salvar 6 resultados | Lista mantém só os 5 melhores (RN07) |
| TU24 | Salvar resultado pior que os 5 melhores | Retorna `false`; lista inalterada |
| TU25 | Rankings por dificuldade | Não se misturam |
| TU26 | JSON corrompido salvo | Retorna lista vazia sem lançar exceção |
| TU27 | `clear()` | Lista fica vazia |

### 2.4 `GameViewModel` (opcional, com `kotlinx-coroutines-test`)

| ID | Cenário | Resultado esperado |
|---|---|---|
| TU28 | Cronômetro não inicia antes da 1ª carta | `seconds` = 0 |
| TU29 | Avançar 5 s virtuais após a 1ª carta | `seconds` = 5 |
| TU30 | `pause()` e avançar 5 s | `seconds` não muda (RN04) |
| TU31 | `MISMATCH` + 800 ms virtuais | Cartas voltam a `HIDDEN` sozinhas |

## 3. Casos de teste manuais

Marcar **OK/Falhou** e anotar o aparelho (ou emulador) usado.

| ID | Requisito | Passos | Resultado esperado | Status |
|---|---|---|---|---|
| TM01 | RF01, RF02 | Abrir o app; escolher Médio + Frutas; tocar em Jogar | Tabuleiro 4×4 com frutas | ☐ |
| TM02 | RF03 | Jogar, sair, jogar de novo com as mesmas opções | Posições das cartas diferentes | ☐ |
| TM03 | RF04 | Tocar em uma carta | Giro suave e face visível | ☐ |
| TM04 | RF05 | Virar duas cartas iguais | Ficam reveladas com destaque verde | ☐ |
| TM05 | RF05 | Virar duas cartas diferentes | Borda vermelha breve; voltam sozinhas | ☐ |
| TM06 | RF06 | Errar um par e tocar rápido em outras cartas | Toques ignorados durante a comparação | ☐ |
| TM07 | RF07 | Fazer 3 jogadas | Contador mostra 3 | ☐ |
| TM08 | RF08 | Observar o cronômetro antes e depois da 1ª carta | Só começa após a 1ª carta | ☐ |
| TM09 | RF09 | Tocar em Pausar | Tabuleiro coberto, tempo parado; Continuar retoma | ☐ |
| TM10 | RF10 | Tocar em Reiniciar → Não / Sim | Não: nada muda. Sim: novo jogo zerado | ☐ |
| TM11 | RF11 | Concluir uma partida | Resultado com jogadas, tempo e estrelas corretos | ☐ |
| TM12 | RF12 | Compartilhar | Abre o seletor; texto contém jogadas, tempo e dificuldade | ☐ |
| TM13 | RF12 | Compartilhar sem app disponível (emulador limpo) | Snackbar de aviso, sem crash | ☐ |
| TM14 | RF13, RF14 | Concluir partidas e abrir Recordes | Aparecem ordenados; abas separadas por dificuldade | ☐ |
| TM15 | RF13 | Fechar e reabrir o app | Recordes continuam lá | ☐ |
| TM16 | RF15 | Limpar recordes → Cancelar / Confirmar | Cancelar: mantém. Confirmar: lista vazia com mensagem | ☐ |
| TM17 | RF16 | Alternar tema claro/escuro | Aplica na hora; persiste após reabrir | ☐ |
| TM18 | RF16 | Desligar vibração e virar cartas | Sem retorno tátil | ☐ |
| TM19 | RF17 | Girar o aparelho no meio da partida | Cartas, jogadas e tempo preservados | ☐ |
| TM20 | RF18 | Tocar em Voltar durante a partida | Diálogo de confirmação | ☐ |
| TM21 | Ciclo de vida | Ir para a tela inicial do celular e voltar | Partida pausada; retoma sem perder dados | ☐ |
| TM22 | RNF03 | Verificar tamanho dos botões e cartas | Alvos ≥ 48 dp (Layout Inspector) | ☐ |
| TM23 | RNF04 | Ligar o TalkBack e navegar | Cartas anunciam posição e estado | ☐ |
| TM24 | RNF07 | Testar em tela pequena, grande e tablet | Nada cortado; cartas legíveis | ☐ |
| TM25 | RNF05 | Conferir contraste em claro e escuro | ≥ 4,5:1 nos textos | ☐ |
| TM26 | RNF08 | Conferir o `AndroidManifest.xml` | Nenhuma `<uses-permission>` | ☐ |
| TM27 | RN05 | Concluir Difícil com poucas e com muitas jogadas | Estrelas conforme a regra | ☐ |

## 4. Matriz de aparelhos sugerida

| Tipo | Exemplo | Testado? |
|---|---|---|
| Emulador, tela pequena | Pixel 4a (API 24 ou 28) | ☐ |
| Emulador, tela grande | Pixel 8 Pro (API mais recente) | ☐ |
| Emulador, tablet | Pixel Tablet | ☐ |
| Aparelho físico | *(preencher modelo e versão do Android)* | ☐ |

## 5. Registro de defeitos

| ID | Data | Descrição | Passos para reproduzir | Gravidade (alta/média/baixa) | Status | Responsável |
|---|---|---|---|---|---|---|
| — | — | — | — | — | — | — |

## 6. Critério de "pronto para entregar"

- 100% dos casos **M** do [02 – Requisitos](02-requisitos.md) passando.
- Todos os testes unitários passando.
- Nenhum defeito de gravidade alta aberto.
- Testes manuais TM01–TM27 executados e registrados.
