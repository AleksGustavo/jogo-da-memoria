# 02 – Requisitos

Prioridade: **M** = Must (obrigatório), **S** = Should (deveria ter), **C** = Could (se sobrar tempo).

## 1. Requisitos funcionais

| ID | Requisito | Prioridade | Critério de aceite |
|---|---|---|---|
| RF01 | O usuário escolhe a dificuldade (Fácil, Médio, Difícil) antes de jogar | M | Cada nível gera o número correto de cartas (12, 16 e 24) |
| RF02 | O usuário escolhe o tema das cartas (Animais, Frutas, Transportes) | M | Todas as cartas da partida usam símbolos do tema escolhido |
| RF03 | O app gera um tabuleiro com pares embaralhados a cada partida | M | Cada símbolo aparece exatamente 2 vezes; a ordem muda entre partidas |
| RF04 | Ao tocar em uma carta virada para baixo, ela é revelada com animação | M | Animação de giro em até 300 ms |
| RF05 | Ao revelar duas cartas, o app verifica se formam um par | M | Iguais: ficam reveladas de forma permanente. Diferentes: viram de volta após ~800 ms |
| RF06 | Durante a verificação, novos toques são ignorados | M | Não é possível revelar uma 3ª carta enquanto duas estão em comparação |
| RF07 | O app exibe o contador de jogadas em tempo real | M | Incrementa a cada par de cartas virado |
| RF08 | O app exibe um cronômetro, iniciado na primeira carta virada | M | Formato mm:ss; para ao fim da partida |
| RF09 | O usuário pode pausar e retomar a partida | S | Pausa esconde o tabuleiro e congela o cronômetro |
| RF10 | O usuário pode reiniciar a partida com confirmação | M | Diálogo Sim/Não; "Sim" gera novo tabuleiro e zera contadores |
| RF11 | Ao encontrar todos os pares, o app abre a tela de resultado | M | Mostra jogadas, tempo, estrelas e aviso de novo recorde |
| RF12 | O usuário pode compartilhar o resultado | S | Abre o seletor de apps do sistema com texto pronto |
| RF13 | O app salva os 5 melhores resultados por dificuldade | S | Sobrevive ao fechamento do app; ordenado por jogadas e depois por tempo |
| RF14 | O usuário vê a lista de recordes em tela própria | S | Lista rolável (RecyclerView), separada por dificuldade |
| RF15 | O usuário pode limpar os recordes com confirmação | C | Diálogo de confirmação; lista fica vazia após confirmar |
| RF16 | O usuário configura vibração e tema claro/escuro/sistema | S | Preferência é aplicada na hora e lembrada na próxima abertura |
| RF17 | Girar a tela não perde a partida | M | Estado das cartas, jogadas e tempo preservados |
| RF18 | Pressionar "voltar" durante a partida pede confirmação | S | Diálogo "Sair da partida?" |

## 2. Requisitos não funcionais

| ID | Categoria | Requisito |
|---|---|---|
| RNF01 | Desempenho | Abertura do app em até 2 s em aparelho de entrada; animações a 60 fps sem travamentos perceptíveis |
| RNF02 | Usabilidade | Qualquer partida pode ser iniciada em até 3 toques a partir da abertura |
| RNF03 | Usabilidade | Alvos de toque com no mínimo 48 dp |
| RNF04 | Acessibilidade | Cartas e botões com `contentDescription`; informação nunca transmitida apenas por cor |
| RNF05 | Acessibilidade | Contraste de texto de pelo menos 4,5:1 (WCAG AA) |
| RNF06 | Compatibilidade | Android 7.0 (API 24) ou superior |
| RNF07 | Adaptabilidade | Funciona em telefones pequenos e grandes, na vertical e na horizontal, e em modo escuro |
| RNF08 | Privacidade | Sem permissões, sem acesso à rede, sem coleta de dados |
| RNF09 | Confiabilidade | Nenhum fechamento inesperado (crash) nos fluxos normais de uso |
| RNF10 | Manutenibilidade | Lógica do jogo separada da interface e coberta por testes unitários |
| RNF11 | Localização | Todos os textos em `strings.xml` (pt-BR), sem texto fixo no código |
| RNF12 | Originalidade | Código próprio; qualquer fonte externa citada (ver [06](06-padroes-de-codigo-e-originalidade.md)) |

## 3. Regras de negócio

| ID | Regra |
|---|---|
| RN01 | Fácil = 6 pares (12 cartas), Médio = 8 pares (16 cartas), Difícil = 12 pares (24 cartas) |
| RN02 | Uma **jogada** é a revelação de duas cartas em sequência, acertando ou não |
| RN03 | O cronômetro inicia na primeira carta virada e para quando o último par é encontrado |
| RN04 | O tempo em pausa não conta |
| RN05 | Estrelas com *n* pares: **3★** se jogadas ≤ 1,5·n; **2★** se jogadas ≤ 2,5·n; **1★** nos demais casos |
| RN06 | Ranking: menos jogadas primeiro; em empate, menor tempo |
| RN07 | Só os 5 melhores resultados de cada dificuldade são mantidos |
| RN08 | Uma carta já revelada ou já combinada não pode ser virada de novo |
| RN09 | A partida só termina quando todos os pares foram encontrados; não há derrota por tempo |

**Exemplo de RN05 (Médio, n = 8):** até 12 jogadas → 3★; de 13 a 20 → 2★; 21 ou mais → 1★.

## 4. Casos de uso resumidos

| ID | Caso de uso | Ator | Fluxo principal |
|---|---|---|---|
| CU01 | Jogar uma partida | Jogador | Menu → escolhe dificuldade e tema → toca em **Jogar** → vira cartas até acabar os pares → vê o resultado |
| CU02 | Ver recordes | Jogador | Menu → **Recordes** → escolhe a dificuldade → vê a lista |
| CU03 | Configurar o app | Jogador | Menu → **Configurações** → altera vibração/tema → volta |
| CU04 | Compartilhar resultado | Jogador | Resultado → **Compartilhar** → escolhe o app → envia |
| CU05 | Abandonar partida | Jogador | Durante o jogo → botão voltar → confirma → volta ao menu |

## 5. Rastreabilidade (requisito → funcionalidade → tela)

| Requisitos | Funcionalidade | Tela |
|---|---|---|
| RF01, RF02 | F1, F2 | Menu |
| RF03–RF10, RF17, RF18 | F3–F7, F12 | Jogo |
| RF11, RF12 | F8, F9 | Resultado |
| RF13–RF15 | F10 | Recordes |
| RF16 | F11 | Configurações |
