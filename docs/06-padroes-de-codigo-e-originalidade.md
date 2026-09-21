# 06 – Padrões de código e originalidade

Serve ao critério **Qualidade do Código (25%)**: organização, padrões de projeto, comentários e documentação. Também trata da **originalidade**, que o enunciado penaliza com zero em caso de cópia.

## 1. Convenções de código (Kotlin)

Seguir o [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html) e o guia de estilo do Android para Kotlin.

| Item | Regra |
|---|---|
| Nomes de classes | `PascalCase` (`GameViewModel`) |
| Funções e variáveis | `camelCase` (`onCardClicked`) |
| Constantes | `UPPER_SNAKE_CASE` (`EXTRA_MOVES`) |
| Recursos XML | `snake_case` com prefixo de tipo: `activity_game.xml`, `item_card.xml`, `ic_pause.xml`, `color_primary` |
| IDs de views | `camelCase` com prefixo de tipo: `btnPlay`, `rvBoard`, `tvMoves` |
| Idioma do código | Identificadores em **inglês**; textos para o usuário em **português** (em `strings.xml`) |
| Idioma dos comentários | Português (o grupo escolhe uma língua e mantém) |
| Tamanho | Funções curtas (ideal < 30 linhas) e classes com uma responsabilidade |
| `val` × `var` | Preferir `val` e coleções imutáveis |
| Nulos | Evitar `!!`; usar `?.`, `?:` e `let` |
| Texto fixo | Proibido no código e nos layouts: sempre `@string/...` |
| Números "mágicos" | Extrair para constantes (`MISMATCH_DELAY_MS = 800`) |
| Formatação | `Code > Reformat Code` do Android Studio antes de cada commit |
| Imports | Sem imports não usados (`Code > Optimize Imports`) |

## 2. Padrões de projeto e princípios aplicados

| Padrão / princípio | Onde aparece |
|---|---|
| **MVVM** | `Activity` (View) ↔ `ViewModel` ↔ `MemoryGame` (Model) |
| **Repository** | `ScoreRepository`, `SettingsRepository` isolam o `SharedPreferences` |
| **Observer** | `LiveData` notifica a interface sobre mudanças |
| **ViewHolder** | `CardAdapter` e `RecordsAdapter` |
| **Callback (lambda)** | `CardAdapter` recebe `onCardClick` e não conhece o `ViewModel` |
| **Single Responsibility** | Cada classe tem um motivo para mudar |
| **Separation of Concerns** | `ui`, `domain` e `data` em pacotes distintos |
| **Função pura** | `StarRating.stars()` sem efeitos colaterais |

## 3. Comentários e documentação do código

- **KDoc** (`/** ... */`) em toda classe pública e nas funções não triviais. Explicar o **porquê** e o **contrato**, não repetir o óbvio.
- Comentário de arquivo no topo das classes principais, indicando a responsabilidade.
- Comentário de linha só onde a intenção não é evidente (ex.: por que existe o atraso de 800 ms).
- Sem código comentado "esquecido" e sem `TODO` sem dono.

Exemplo de padrão desejado:

```kotlin
/**
 * Regras do jogo da memória. Não depende de nada do Android,
 * o que permite testá-la com JUnit puro.
 *
 * @param difficulty define quantos pares há no tabuleiro
 * @param theme define os símbolos usados nas cartas
 */
class MemoryGame(difficulty: Difficulty, theme: GameTheme) { /* ... */ }
```

## 4. Originalidade e citação de fontes

O enunciado diz que o código deve ser **original** e que cópias sem citação, ou de outros alunos, geram **penalidades severas, inclusive zero**.

### 4.1 Regras do grupo

1. Escrever o código do grupo. Consultar documentação e tutoriais para **aprender** é permitido; colar código pronto sem citar não é.
2. Se um trecho for adaptado de uma fonte (site, documentação, livro, IA, colega), **citar no comentário do código** e na tabela abaixo.
3. Nunca copiar código de projetos de outros alunos, atuais ou anteriores.
4. Bibliotecas oficiais do AndroidX/Material usadas como dependência **não** contam como cópia, mas devem constar na tabela de dependências.
5. Imagens, ícones, fontes e sons: usar apenas material próprio ou de licença livre, e registrar a licença.
6. Ferramentas de IA: se forem usadas para gerar ou revisar trechos, registrar isso, e o grupo deve ser capaz de **explicar cada linha** na apresentação.
7. Cada integrante deve conseguir explicar qualquer parte do código.

### 4.2 Formato de citação no código

```kotlin
// Fonte: https://developer.android.com/develop/ui/views/layout/recyclerview
// Adaptado de: exemplo de RecyclerView.Adapter (documentação oficial). Acesso em 18/09/2026.
```

### 4.3 Registro de referências e créditos (preencher ao longo do projeto)

| Nº | Trecho / arquivo | Fonte (URL, livro, pessoa, ferramenta) | O que foi aproveitado | Data de acesso |
|---|---|---|---|---|
| 1 | *(ex.: `CardAdapter.kt`)* | *(ex.: developer.android.com)* | *(ex.: estrutura básica do adapter, adaptada)* | *(preencher)* |
| — | — | — | — | — |

### 4.4 Dependências de terceiros

| Biblioteca | Versão | Licença | Uso |
|---|---|---|---|
| AndroidX (AppCompat, ConstraintLayout, RecyclerView, Lifecycle) | *(preencher)* | Apache 2.0 | Base da interface |
| Material Components | *(preencher)* | Apache 2.0 | Componentes visuais |
| Kotlin Coroutines | *(preencher)* | Apache 2.0 | Cronômetro e atrasos |
| JUnit 4 | *(preencher)* | EPL 1.0 | Testes |

### 4.5 Materiais visuais

| Recurso | Origem | Licença |
|---|---|---|
| Emojis das cartas | Fonte Unicode do sistema do aparelho | Definida pelo fabricante do aparelho |
| Ícone do app | Criado pelo grupo | Própria |
| Ícones de interface (pausar, reiniciar, troféu) | Material Icons (Google) | Apache 2.0 |

## 5. Fluxo de trabalho em grupo

| Tópico | Prática sugerida |
|---|---|
| Controle de versão | Git (repositório privado do grupo, ex.: GitHub) |
| Branches | `main` (estável) e `feature/<nome>` por tarefa |
| Mensagens de commit | Curtas e no imperativo: `Adiciona cálculo de estrelas` |
| Revisão | Todo Pull Request revisado por outro integrante antes do *merge* |
| Autoria | Manter o histórico do Git; ele comprova quem escreveu o quê |
| `.gitignore` | Incluir `build/`, `.gradle/`, `local.properties`, `.idea/` |

## 6. Checklist de qualidade de código (antes de cada entrega parcial)

- [ ] Sem avisos (*warnings*) do compilador nem do *Lint* que sejam relevantes.
- [ ] Sem textos fixos no código ou nos layouts.
- [ ] Sem código morto, sem `!!` desnecessário e sem `println` de depuração.
- [ ] KDoc nas classes públicas.
- [ ] Testes unitários passando.
- [ ] Todas as fontes externas citadas (§4.3).
- [ ] Código formatado e imports otimizados.
