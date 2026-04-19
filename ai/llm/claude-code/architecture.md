## Github source

https://github.com/tanbiralam/claude-code

## Architecture

![claude-code-architecture](https://miro.medium.com/v2/resize:fit:1100/format:webp/1*7x8C9RoPM0n5tybVwqFbrg.png)

User interface — how you talk to Claude Code. VS Code, terminal, browser, or voice.

Query engine — the brain. 46,000 lines that handle errors silently so you never see a crash.

Tool system — 40+ actions Claude can take. Read files, run bash commands, edit code.

Memory — Claude remembers your work across sessions. Your notes, its notes, and a nightly cleanup.

Coordinator mode — Claude splits big tasks across multiple agents working at the same time.

Feature flags — controls what you see vs what Anthropic keeps internal.

Telemetry — tracks how you use it. When you get frustrated, when you hit continue, when things fail.

## Components

### 1. Tool System (`src/tools/`)

Every tool Claude Code can invoke is implemented as a self-contained module. Each tool defines its input schema, permission model, and execution logic.

| Tool                                     | Description                                    |
| ---------------------------------------- | ---------------------------------------------- |
| `BashTool`                               | Shell command execution                        |
| `FileReadTool`                           | File reading (images, PDFs, notebooks)         |
| `FileWriteTool`                          | File creation / overwrite                      |
| `FileEditTool`                           | Partial file modification (string replacement) |
| `GlobTool`                               | File pattern matching search                   |
| `GrepTool`                               | ripgrep-based content search                   |
| `WebFetchTool`                           | Fetch URL content                              |
| `WebSearchTool`                          | Web search                                     |
| `AgentTool`                              | Sub-agent spawning                             |
| `SkillTool`                              | Skill execution                                |
| `MCPTool`                                | MCP server tool invocation                     |
| `LSPTool`                                | Language Server Protocol integration           |
| `NotebookEditTool`                       | Jupyter notebook editing                       |
| `TaskCreateTool` / `TaskUpdateTool`      | Task creation and management                   |
| `SendMessageTool`                        | Inter-agent messaging                          |
| `TeamCreateTool` / `TeamDeleteTool`      | Team agent management                          |
| `EnterPlanModeTool` / `ExitPlanModeTool` | Plan mode toggle                               |
| `EnterWorktreeTool` / `ExitWorktreeTool` | Git worktree isolation                         |
| `ToolSearchTool`                         | Deferred tool discovery                        |
| `CronCreateTool`                         | Scheduled trigger creation                     |
| `RemoteTriggerTool`                      | Remote trigger                                 |
| `SleepTool`                              | Proactive mode wait                            |
| `SyntheticOutputTool`                    | Structured output generation                   |

### 2. Command System (`src/commands/`)

User-facing slash commands invoked with `/` prefix.

| Command              | Description                  |
| -------------------- | ---------------------------- |
| `/commit`            | Create a git commit          |
| `/review`            | Code review                  |
| `/compact`           | Context compression          |
| `/mcp`               | MCP server management        |
| `/config`            | Settings management          |
| `/doctor`            | Environment diagnostics      |
| `/login` / `/logout` | Authentication               |
| `/memory`            | Persistent memory management |
| `/skills`            | Skill management             |
| `/tasks`             | Task management              |
| `/vim`               | Vim mode toggle              |
| `/diff`              | View changes                 |
| `/cost`              | Check usage cost             |
| `/theme`             | Change theme                 |
| `/context`           | Context visualization        |
| `/pr_comments`       | View PR comments             |
| `/resume`            | Restore previous session     |
| `/share`             | Share session                |
| `/desktop`           | Desktop app handoff          |
| `/mobile`            | Mobile app handoff           |

### 3. Service Layer (`src/services/`)

| Service                  | Description                                             |
| ------------------------ | ------------------------------------------------------- |
| `api/`                   | Anthropic API client, file API, bootstrap               |
| `mcp/`                   | Model Context Protocol server connection and management |
| `oauth/`                 | OAuth 2.0 authentication flow                           |
| `lsp/`                   | Language Server Protocol manager                        |
| `analytics/`             | GrowthBook-based feature flags and analytics            |
| `plugins/`               | Plugin loader                                           |
| `compact/`               | Conversation context compression                        |
| `policyLimits/`          | Organization policy limits                              |
| `remoteManagedSettings/` | Remote managed settings                                 |
| `extractMemories/`       | Automatic memory extraction                             |
| `tokenEstimation.ts`     | Token count estimation                                  |
| `teamMemorySync/`        | Team memory synchronization                             |

### 4. Bridge System (`src/bridge/`)

A bidirectional communication layer connecting IDE extensions (VS Code, JetBrains) with the Claude Code CLI.

- `bridgeMain.ts` — Bridge main loop
- `bridgeMessaging.ts` — Message protocol
- `bridgePermissionCallbacks.ts` — Permission callbacks
- `replBridge.ts` — REPL session bridge
- `jwtUtils.ts` — JWT-based authentication
- `sessionRunner.ts` — Session execution management

### 5. Permission System (`src/hooks/toolPermission/`)

Checks permissions on every tool invocation. Either prompts the user for approval/denial or automatically resolves based on the configured permission mode (`default`, `plan`, `bypassPermissions`, `auto`, etc.).

### 6. Feature Flags

Dead code elimination via Bun's `bun:bundle` feature flags:

```typescript
import { feature } from "bun:bundle";

// Inactive code is completely stripped at build time
const voiceCommand = feature("VOICE_MODE")
  ? require("./commands/voice/index.js").default
  : null;
```

Notable flags: `PROACTIVE`, `KAIROS`, `BRIDGE_MODE`, `DAEMON`, `VOICE_MODE`, `AGENT_TRIGGERS`, `MONITOR_TOOL`

---
