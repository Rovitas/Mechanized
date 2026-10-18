# 📋 Mechanized 架构对齐与总体开发实施方案 (Implementation Plan)

## 📋 快速导航目录
- [[#🎯 目标概述 (Goal Description)]]
- [[#👥 用户审核事项 (User Review Required)]]
- [[#🗺️ 全局开发清单与阶段映射 (Roadmap & Checklist)]]
  - [[#🔴 Step 0：基础规范、命名对齐与佩戴渲染（当前立即执行任务）]]
  - [[#🔴 Phase 1：基础材料 & 能量 & 核心制造 (v0.1.0-alpha)]]
  - [[#🟡 Phase 2：智能分析、模块扩展与 HUD 目镜 (v0.2.0-beta)]]
  - [[#🟣 Phase 3：遗迹探索、源质生态与传送中枢 (v0.3.0)]]
  - [[#👑 Phase 4：机械纪元维度、使魔坞站与升格终局 (v1.0.0)]]

---

## 🎯 目标概述 (Goal Description)

依据 [`docs/design_and_architecture.md`](file:///d:/Entertainment/MC/ModDevelopment/Mine/Mechanized-1.21.1-neoforge/docs/design_and_architecture.md) 的总体设计，建立覆盖 **全生命周期的任务清单与实施路线图**。
特别针对用户提出的两项前置修正诉求以及 Curios 佩戴渲染问题，将它们作为 **「Step 0：基础规范与命名对齐」** 优先嵌入到开发清单最前沿，形成自洽且平滑的推进闭环：
1. **代码注释全量英文规范化**（Javadocs & inline comments 全部替换为规范英文）；
2. **文档敲定命名对齐**（`energetic_flower` $\to$ `zoetic_bloom` 辉源花；`basic_dome` $\to$ `lite_space_dome` 轻量天幕手环，同步更新模型/贴图/动画/语言文件）；
3. **Curios 佩戴手环渲染修复**（解决手环佩戴在手臂上时的定位、朝向、尺寸及动画同步）；
4. **后续核心玩法阶段性递进**（Phase 1 基础工业与手搓装配 $\to$ Phase 2 模块与目镜 $\to$ Phase 3 遗迹与源质 $\to$ Phase 4 维度与使魔）。

---

## 👥 用户审核事项 (User Review Required)

> [!IMPORTANT]
> **资源重命名涉及的兼容性**：
> 将 `basic_dome` 重命名为 `lite_space_dome`、`energetic_flower` 重命名为 `zoetic_bloom` 会导致现有测试存档中已存在的旧方块/物品 ID 变更（属于开发早期正常的重构更名）。由于目前处于纯开发期，这是对齐规划文档的黄金时机。

> [!NOTE]
> **文件结构与类名对齐**：
> - `BasicDomeItem.java` $\to$ `LiteSpaceDomeItem.java`（或提供通用基类 `SpaceDomeItem`）
> - `BasicDomeItemModel.java` $\to$ `LiteSpaceDomeItemModel.java`
> - `BasicDomeItemRenderer.java` $\to$ `LiteSpaceDomeItemRenderer.java`
> - `BasicDomeCurioRenderer.java` $\to$ `LiteSpaceDomeCurioRenderer.java`
> - 资源文件同步重命名为 `lite_space_dome.geo.json`、`lite_space_dome.animation.json`、`lite_space_dome.png`

---

## 🗺️ 全局开发清单与阶段映射 (Roadmap & Checklist)

> **阶段演进路线一览**：  
> * **【Step 0】基础规范与架构修正**（当前）：代码注释英文规范化 + 官方命名对齐（Zoetic Bloom / Lite Space Dome / Harmonizer） + Curios 佩戴渲染修复；  
> * **【Phase 1: Alpha 核心闭环】**：基础材料（钢/硅） + 供电网络 + 技师工作台手搓装配 + 轻量天幕手环 (Lite)；  
> * **【Phase 2: Beta 实用飞跃】**：智能分析仪逆向 + 智能目镜 HUD + 环形轮盘 + 实用模块 + 回收炉；  
> * **【Phase 3: 探索与启示】**：Zoetium 源质液体 + 辉源花生态 + 废弃实验室 + 信号定位仪 + 传送门；  
> * **【Phase 4: 机械纪元终局】**：机械纪元维度 + 板件机械使魔 + 制式/始初手环升格 + 统治者决战。

### 🔴 Step 0：基础规范、命名对齐与佩戴渲染（当前立即执行任务）

- [x] **Task 0.1：代码注释规范化 (English Comments Standard)**
  - 将所有 Java 类中的中文注释替换为规范地道的英文 Javadoc 与代码内联说明。
- [x] **Task 0.2：官方命名与本地化对齐 (Registry & Localization Alignment)**
  - **辉源花**：`energetic_flower` $\to$ `zoetic_bloom`（英文: `Zoetic Bloom`，中文: `辉源花`）。
  - **轻量手环**：`basic_dome` $\to$ `lite_space_dome`（英文: `Lite Space Dome`，中文: `轻量天幕手环`）。
  - **设备调谐台**：`dome_tuner` $\to$ `harmonizer`（英文: `The Harmonizer`，中文: `设备调谐台`）。
  - 同步重命名资源：`textures/`, `models/`, `blockstates/`, `geo/`, `animations/`, `curios/tags/`。
- [x] **Task 0.3：Curios 佩戴手环渲染修复 (Curios Wrist Rendering Fix)**
  - 还原 1.20.1 基线数值（`offsetY = 0.35`，`rotationX = 90.0`，`rotationY = 150.0`）；
  - 迁移 NeoForge 客户端聊天调试拦截机制（支持 `bracelet offset/scale/rot/show/help` 游戏内实时微调）；
  - 接入 NeoForge 客户端 `RenderArmEvent` 官方事件，实现第一人称主视角左手手环佩戴渲染与暗光幽蓝自发光。

---

### 🔴 Phase 1：基础材料 & 能量 & 核心制造 (v0.1.0-alpha)

> 目标：玩家能够从零开始——炼钢炼硅 $\to$ 发电充电 $\to$ 技师工作台极客装配 $\to$ 手搓出【轻量天幕手环 (Lite)】

- [ ] **材料与配方链**
  - 注册粗钢坯 (`raw_steel_blend`)、钢锭 (`steel_ingot`)、钢块 (`steel_block`)、钢板 (`steel_plate`)、钢棒 (`steel_rod`)、硅晶片 (`silicon_wafer`)；
  - 编写高炉炼钢（铁+煤$\to$粗钢坯$\to$钢锭）、高炉炼硅（下界石英$\to$硅晶片）配方 JSON；
- [ ] **供电网络体系**
  - 实现 NeoForge `IEnergyStorage` 统一对接与物品 DataComponent 储能；
  - 实现燃料发电机 (`GeneratorBlock` + BE + GUI + 邻接推电 + 内置充能槽)；
  - 实现充电台 (`ChargingStationBlock` + BE + GUI)；
- [ ] **工具系统**
  - 注册工匠锻造锤（带 0.5s 防误触冷却）、充电式电焊枪（长按累积进度）、切割锯；
- [ ] **技师工作台 (Machinist Bench)**
  - 注册铁砧摆法合成；
  - 实现纯世界右键交互逻辑、营火式立体零件渲染、粒子与音效（敲打火星、焊接电弧、切割飞屑）；
  - 实现自定义装配配方类型 `AssemblyRecipe`（4 步工序）与废品回收逻辑；
- [ ] **手环体系与通用类**
  - 提取通用 `SpaceDomeItem` 基类与 `DomeTier` 枚举；
  - 完成轻量天幕手环手搓配方；
- [ ] **创造物品栏规范化排版 (Creative Tab Organization)**
  - 实现 `CreativeTabSeparatorItem` 章节标题项与 9 格自动补齐对齐逻辑；
  - 规范化组织【Ⅰ.材料与工具】至【Ⅵ.源质生态与遗迹】六大章节。

---

### 🟡 Phase 2：模块系统、机器与智能目镜 (v0.2.0-beta)

> 目标：智能分析仪逆向图纸、设备调谐台装载、纳米消融装配渲染器、智能目镜解锁与 HUD、环形轮盘

- [ ] **智能分析仪 (Smart Analyzer)**
  - 原版物品逆向解码机制（铁镐$\to$挖掘模块，羽毛/烟花$\to$推进模块等）；
- [ ] **设备调谐台 (The Harmonizer，原 Dome Tuner)**
  - [x] **美术与动画资产 (Art & Animation): 已就绪 ✅【Art 席位已验收】**
    - [x] 材质贴图：钛青深空夜色 + 阳极氧化铝冷白 32×32 展开高反差贴图（`harmonizer.png`）；
    - [x] 几何模型：原点居中、两格高宏伟展台、中间 360° 开阔通透的 GeckoLib 骨骼模型（`harmonizer.geo.json`）；
    - [x] 弹性机械动效：蓄能喷射升空（`deploy`）、2 像素磁气垫浮动（`idle`）、电磁活塞冲压高频共振（`working`）三套力量感动画（`harmonizer.animation.json`）；
    - [x] 规范落盘与归档：已于 Blockbench 实机验收，代码库已通过 Git Commit (`eb2fc4b`) 归档。
  - [ ] **Java 业务代码与渲染器 (Java Code & Renderer): 待实现 ⏳【待 Code 席位接手】**
    - [ ] 方块本体：实现 `HarmonizerBlock`（两格高多方块/碰撞盒属性、右键交互放置手环）；
    - [ ] 方块实体：实现 `HarmonizerBlockEntity`（物品存储、网络数据包同步与源质注能缓存）；
    - [ ] GeckoLib 渲染器：编写 `HarmonizerBlockRenderer`（绑定 Geo 模型与动画控制器，并在中央镂空处驱动物品/手环的 3D 浮空自转渲染）；
    - [ ] 调谐交互与音效：手环与设备模块插拔、源质流体注能、注册物理卡扣音（Click-Clack）与微型伺服电机自检蜂鸣。
- [ ] **核心模块群**
  - ⛏️ 辅助挖掘模块、🚀 推进机动模块（重构整合原鞋子推进器）、🧲 磁吸模块、🛡️ 动能护盾模块、💧 源质虹吸模块；
- [ ] **纳米消融/凝聚装配渲染器 (Nano Dissolve Renderer)**
  - 零动画依赖设计，支持静态几何模型直接赋予消融动效；
  - 顶点局部 Y 空间裁剪（Local Space Y），确保沿手臂方向推进；
  - 接入双轨自适应（无光影走 Core Shader，开启光影走通用双 Pass Alpha Cutoff + Eyes 发光 Neon Bloom 通道）；
- [ ] **智能目镜 (Smart Goggles)**
  - Curios `goggles` 槽位，夜视模式切换，主手持焊枪时展开全面罩动画；
  - 智能目镜 HUD（装配引导、野外灵感扫描高亮）；
- [ ] **环形交互轮盘 (Radial Menu)**
  - 对标 Create 的 `RadialMenu`，按 `V` 键展开扇形轮盘选择与切换模块；
  - 触发轻微抬腕看表动作姿态与半透明悬浮光子操作环（支持滚轮盲切）；
- [ ] **JEI & EMI 配方集成**。

---

### 🟢 Phase 3：世界生成、液体与废弃实验室探索

> 目标：Zoetium 源质自然生成、异常信号定位仪、废弃实验室与传送门点火

- [ ] **Zoetium 源质流体**
  - 青蓝色半透明流体（`#06D5F9`）+ 改色粒子，跌入时触发剧情大标题与灵感字幕；
  - 地表辉源花与地下源质池自然生成；
  - 辉源花抽取能量后退化为枯木的机制；
- [ ] **废弃机械实验室 (Abandoned Lab)**
  - 主世界遗迹结构生成；
  - 制作【异常信号定位仪 (Signal Locator)】，手持音频/方位指引寻路；
  - 传送门修复芯片提取、分析仪修复、插回控制台 + 源质一次性点火。

---

### 🔵 Phase 4：机生种生态与机械纪元终局 (v1.0.0 正式版)

> 目标：机械纪元维度、原生机生种（Mechazoa）与灵械兽（Mechazoan）、仿灵械兽（Mechazoan Replica）折叠立方体与仿械仓（Replica Cradle）、统治者决战与领袖升格

- [ ] **机械纪元维度 (The Mechazoic)**
  - 专属维度群系、失落文明废墟、机械矿物与硅基植被；
- [ ] **原生机生种 (Mechazoa) 与仿灵械兽 (Mechazoan Replica)**
  - 自然硅基生命生态架构；
  - 分子烧结 3D 打印机切片分件打印（微折叠骨架、中枢芯片、微反应炉、合金外壳）；
  - 技师工作台精密总装工序（锯切修模、电焊走线、注能唤醒、锤压咬合）；
  - 变形金刚式立方体收纳折叠形态（Cube Item）；
  - 四足兽型与双翼飞禽通用高复用骨骼；
- [ ] **分子烧结 3D 打印机 (Molecular Sinterer)**
  - 多方块结构（升降打印托盘 + 激光龙门架 + 耐高温玻璃罩）；
  - 反向 Local Y 消融 Shader 逐层生长渲染与步进电机音效；
  - 打印制式天幕手环 (Common Space Dome) 与高阶模块；
- [ ] **仿械仓 (Replica Cradle Block + BE)**
  - 基地停泊、休眠、充能、自动维护与轻量自动化物料调度；
- [ ] **高阶制造与领袖升格**
  - 区域阶段 BOSS 与统治者 BOSS 决战，夺取核心蓝图完成领袖升格，铸就始初天幕手环 (Prime)；
  - 实现多方块中控台与 Prime 穹顶核心的手环物理插入槽（Docking Slot），手环嵌入作为超频算力与支配者主权认证密钥。

---

## 🛠️ Step 0 具体实施方案 (Proposed Changes)

### 1. 注释全量英文规范化
- 修改类：`BasicDomeItem.java`、`BasicDomeItemRenderer.java`、`DomeArmorItem.java`、`ClientModEvents.java` 等；
- 将状态机、渲染逻辑、属性修饰符等中文注释全部改写为纯正英文文档注释。

### 2. 官方命名与本地化对齐
- **注册类重构**：
  - `ModBlocks.ENERGETIC_FLOWER` $\to$ `ModBlocks.ZOETIC_BLOOM`（注册名 `"zoetic_bloom"`）；
  - `ModItems.BASIC_DOME` $\to$ `ModItems.LITE_SPACE_DOME`（注册名 `"lite_space_dome"`）；
  - `ModBlocks.DOME_TUNER` $\to$ `ModBlocks.HARMONIZER`（注册名 `"harmonizer"`）；
- **类名重命名**：
  - `BasicDomeItem.java` $\to$ `LiteSpaceDomeItem.java`
  - `BasicDomeItemModel.java` $\to$ `LiteSpaceDomeItemModel.java`
  - `BasicDomeItemRenderer.java` $\to$ `LiteSpaceDomeItemRenderer.java`
  - `BasicDomeCurioRenderer.java` $\to` `LiteSpaceDomeCurioRenderer.java`
- **资源路径迁移**：
  - `assets/mechanized/textures/item/basic_dome.png` $\to` `lite_space_dome.png`
  - `assets/mechanized/geo/basic_dome.geo.json` $\to` `lite_space_dome.geo.json`
  - `assets/mechanized/animations/basic_dome.animation.json` $\to` `lite_space_dome.animation.json`
  - `assets/mechanized/models/item/basic_dome.json` $\to` `lite_space_dome.json`
  - `energetic_flower` 相关所有方块模型与贴图同步迁移为 `zoetic_bloom`
- **语言文件更新**：
  - `zh_cn.json` 与 `en_us.json` 全面对齐。

### 3. Curios 佩戴手环渲染修复
- 优化 `LiteSpaceDomeCurioRenderer`：
  - 确保模型正确吸附在手臂关节（支持 Alex/Steve 皮肤手臂模型）；
  - 修正手环内径与手腕尺寸，防止手腕穿模；
  - 确保佩戴状态下也继承 GeckoLib 实例的开启/关闭与 UV 滚动状态。

---

## 🔬 验证计划 (Verification Plan)

1. **编译验证**：
   - 执行 `./gradlew compileJava` 验证重命名和注释修改无任何语法/链接错误；
2. **资源与数据包加载验证**：
   - 验证方块状态、贴图与 Curios `bracelet` 标签能够被游戏平稳识别；
3. **游戏内实机测试**：
   - 验证创造栏名称显示为「轻量天幕手环」和「辉源花」；
   - 验证右键开启/关闭动画与全息量子滚动；
   - 验证佩戴在 Curios 手环槽位时在第三人称手臂上的渲染表现。
