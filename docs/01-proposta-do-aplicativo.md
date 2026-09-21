# 01 – Proposta do aplicativo

## 1. Visão geral

**Jogo da Memória** é um aplicativo Android nativo, escrito em Kotlin, em que o jogador vira cartas duas a duas para encontrar todos os pares no menor número de jogadas e no menor tempo possível.

O app funciona 100% offline. Não usa banco de dados, API externa nem permissões especiais. Só guarda localmente os recordes e as preferências.

## 2. Objetivo

- **Objetivo do aplicativo:** oferecer um jogo de memória rápido, acessível e visualmente agradável, com níveis de dificuldade, temas de cartas e placar de recordes local.
- **Objetivo acadêmico:** demonstrar design de interface, programação em Kotlin e uso de recursos básicos do Android SDK (Activities, Intents, RecyclerView, ViewModel, SharedPreferences, animações, menus e diálogos).

## 3. Público-alvo

| Segmento | Descrição | O que o app oferece a ele |
|---|---|---|
| Crianças (a partir de ~6 anos, com apoio de um adulto) | Estão desenvolvendo atenção e memória visual | Nível Fácil, cartas grandes, símbolos coloridos, feedback claro |
| Jovens e adultos | Querem uma pausa curta durante o dia | Partidas de 1 a 3 minutos, níveis Médio e Difícil, recordes |
| Idosos | Buscam exercitar a memória de forma leve | Botões e cartas grandes, contraste alto, sem pressa (o tempo não elimina o jogador) |

**Contexto de uso:** celular na vertical (uso principal), em filas, transporte ou intervalos. Deve funcionar sem internet.

## 4. Problema e necessidade atendida

- **Problema:** muitos jogos de memória gratuitos têm anúncios, exigem cadastro, pedem conexão ou têm interface poluída. Isso incomoda o público e atrapalha o foco.
- **Necessidade:** um jogo simples e leve, que abre e joga na hora, sem cadastro, sem anúncios e sem internet.
- **Benefício adicional:** treino de memória de curto prazo e atenção visual, com progressão de dificuldade que dá motivo para voltar (recordes e estrelas).

## 5. Funcionalidades principais

| Nº | Funcionalidade | Descrição |
|---|---|---|
| F1 | Escolha de dificuldade | Fácil (6 pares), Médio (8 pares) e Difícil (12 pares) |
| F2 | Escolha de tema | Animais, Frutas e Transportes. O tema muda os símbolos das cartas |
| F3 | Tabuleiro embaralhado | A cada partida as cartas são sorteadas em posições diferentes |
| F4 | Virar carta | Toque na carta vira com animação de giro |
| F5 | Verificação de pares | Par igual: as cartas ficam reveladas. Par diferente: viram de volta após cerca de 0,8 s |
| F6 | Contador de jogadas e cronômetro | Aparecem no topo da tela durante toda a partida. O cronômetro inicia na primeira carta virada |
| F7 | Pausar e reiniciar | Pausar esconde o tabuleiro (evita "espiar") e congela o tempo. Reiniciar pede confirmação |
| F8 | Resultado da partida | Tela final com jogadas, tempo, nota de 1 a 3 estrelas e aviso de novo recorde |
| F9 | Compartilhar resultado | Envia o resultado como texto por qualquer app instalado (Intent implícita) |
| F10 | Recordes locais | Top 5 por dificuldade, listados em tela própria |
| F11 | Configurações | Vibração (retorno tátil) ligada/desligada e tema do app (claro, escuro ou do sistema) |
| F12 | Persistência de estado | Girar a tela ou trocar de app não perde a partida em andamento |

> Requisitos detalhados, com identificadores, estão em [02 – Requisitos](02-requisitos.md).

## 6. Diferenciais (concepção e criatividade)

- **Sem fricção:** abrir → escolher → jogar em até 3 toques.
- **Pausa que esconde o tabuleiro:** evita trapaça e respeita o jogador.
- **Nota por estrelas** calculada pela eficiência (jogadas em relação ao número de pares), não só pelo tempo. Isso dá valor a jogar com calma.
- **Privacidade total:** sem rede, sem anúncios, sem coleta de dados, sem permissões.
- **Acessibilidade desde o projeto:** alvos de toque grandes, contraste adequado, descrições para leitores de tela, modo escuro.
- **Cartas originais:** símbolos em Unicode/vetores próprios, sem imagens de terceiros (evita problemas de direitos autorais).

## 7. Fora do escopo desta versão

Ideias interessantes, mas não obrigatórias para a avaliação. Ficam como evolução futura:

- Modo de dois jogadores no mesmo aparelho.
- Sons e música.
- Tema "Programação Android" (pares de termo e ícone), com fins didáticos.
- Placar online e contas de usuário (contradiz o requisito de não usar APIs externas).

## 8. Restrições assumidas

- Sem banco de dados e sem APIs externas (exigência do enunciado).
- Persistência apenas em arquivos internos (`SharedPreferences`), que o enunciado permite como opcional.
- Trabalho em grupo, com código original e citação de qualquer fonte externa (ver [06](06-padroes-de-codigo-e-originalidade.md)).
