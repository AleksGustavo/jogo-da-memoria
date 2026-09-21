# 09 – Checklist de entrega

Conferência final contra o enunciado da **Avaliação I – Programação para Dispositivos Móveis** (Prof. Jonas Bodê).

## 1. Entregáveis

| Item exigido | Formato | Pronto? |
|---|---|---|
| Código-fonte completo do projeto | Pasta do projeto do Android Studio compactada (`.zip`), sem as pastas `build/` e `.gradle/` | ☐ |
| Documento explicando o aplicativo | Documentos de `docs/` (idealmente consolidados em um único `.docx` ou `.pdf`) | ☐ |
| Envio pela plataforma designada | Microsoft Teams | ☐ |
| APK de depuração *(recomendado, não exigido)* | `app-debug.apk` | ☐ |

> Antes de enviar, abrir o `.zip` gerado, descompactar em outra pasta e conferir se o projeto abre e compila no Android Studio.

## 2. Rubrica de avaliação × evidências

### 2.1 Concepção e Criatividade (20%)

| Critério | Evidência | Conferido |
|---|---|---|
| Qualidade da ideia | [01](01-proposta-do-aplicativo.md) §1, §6 | ☐ |
| Inovação | Pausa que esconde o tabuleiro, estrelas por eficiência, privacidade total, acessibilidade | ☐ |
| Praticidade | Jogo iniciado em até 3 toques (RNF02) | ☐ |
| Clareza do propósito | [01](01-proposta-do-aplicativo.md) §2, §3, §4 | ☐ |

### 2.2 Funcionalidade (30%)

| Critério | Evidência | Conferido |
|---|---|---|
| Todas as funcionalidades propostas implementadas | Tabela de F1–F12 em [01](01-proposta-do-aplicativo.md) §5 | ☐ |
| Funcionando sem erros | Testes unitários + TM01–TM27 em [05](05-plano-de-testes.md) | ☐ |
| Uso de `Intent` | Menu → Jogo → Resultado; `ACTION_SEND` | ☐ |
| Uso de `RecyclerView` | Tabuleiro e lista de recordes | ☐ |
| Tratamento de eventos | [04](04-arquitetura-e-implementacao.md) §6 | ☐ |
| Persistência (opcional) | `SharedPreferences` (recordes e configurações) | ☐ |
| Sem banco de dados / API externa | Nenhuma dependência de rede; sem `<uses-permission>` | ☐ |

### 2.3 Interface do Usuário (25%)

| Critério | Evidência | Conferido |
|---|---|---|
| Interface atrativa e fácil de usar | [03](03-design-e-usabilidade.md) §1–§5 | ☐ |
| Navegação intuitiva | Fluxo em [03](03-design-e-usabilidade.md) §3 | ☐ |
| Design adaptável | Vertical/horizontal, telas grandes, modo escuro | ☐ |
| Feedback visual claro | Animações e destaque de acerto/erro ([03](03-design-e-usabilidade.md) §6.1) | ☐ |
| Uso adequado de componentes | Material Components, `RecyclerView`, `ChipGroup`, `TabLayout`, diálogos | ☐ |
| Carregamentos rápidos | Sem *splash*; tabuleiro gerado em memória | ☐ |
| Contraste e acessibilidade verificados | TM22–TM25 | ☐ |

### 2.4 Qualidade do Código (25%)

| Critério | Evidência | Conferido |
|---|---|---|
| Organização | Pacotes `ui/`, `domain/`, `data/`, `util/` ([04](04-arquitetura-e-implementacao.md) §2) | ☐ |
| Padrões de projeto | MVVM, Repository, Observer, ViewHolder ([06](06-padroes-de-codigo-e-originalidade.md) §2) | ☐ |
| Comentários e KDoc | [06](06-padroes-de-codigo-e-originalidade.md) §3 | ☐ |
| Testes | Pasta `test/` com JUnit | ☐ |
| Convenções e formatação | [06](06-padroes-de-codigo-e-originalidade.md) §1 | ☐ |
| Sem alertas relevantes de Lint | Executar `Analyze > Inspect Code` | ☐ |

## 3. Originalidade (nota importante do enunciado)

| Item | Conferido |
|---|---|
| Todo o código foi escrito pelo grupo | ☐ |
| Toda fonte externa está citada no código **e** na tabela de [06](06-padroes-de-codigo-e-originalidade.md) §4.3 | ☐ |
| Nenhum trecho copiado de outros alunos | ☐ |
| Imagens, ícones e fontes próprios ou de licença livre, com licença registrada | ☐ |
| Uso de ferramentas de IA (se houve) registrado, e o grupo sabe explicar cada trecho | ☐ |
| Cada integrante consegue explicar qualquer parte do código | ☐ |
| Histórico do Git preservado (comprova autoria) | ☐ |

## 4. Verificações técnicas finais

- [ ] O projeto abre e compila do zero em outra máquina (clonar/descompactar e rodar).
- [ ] `./gradlew test` passa.
- [ ] O app roda em emulador de API 24 e em um mais recente.
- [ ] `AndroidManifest.xml` sem permissões desnecessárias.
- [ ] Nome do app, ícone e nome do pacote definidos e coerentes.
- [ ] Versão do app (`versionName`) definida (ex.: `1.0`).
- [ ] Sem chaves, senhas ou dados pessoais no repositório.
- [ ] `docs/` atualizada com o que foi realmente implementado (comparar com [01](01-proposta-do-aplicativo.md) §5).
- [ ] Capturas de tela das 5 telas incluídas no documento final *(quando o app existir)*.

## 5. Preparação da apresentação (se o professor pedir)

| Etapa | Duração sugerida | Quem |
|---|---|---|
| Problema e público-alvo | 1 min | |
| Demonstração do app (partida completa, recordes, compartilhar, rotação, modo escuro) | 3 min | |
| Arquitetura e recursos do Android usados | 2 min | |
| Testes e qualidade | 1 min | |
| Perguntas | — | Todos |

**Roteiro da demonstração:** abrir o app → escolher Médio + Frutas → jogar (errar de propósito uma vez) → pausar → girar a tela → concluir → ver estrelas → compartilhar → ver Recordes → alternar tema escuro.

## 6. Assinatura de conferência

| Integrante | Conferiu? | Data |
|---|---|---|
| | ☐ | |
