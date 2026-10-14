![Mechanized Icon](src/main/resources/icon.png)

# ⚙️ Mechanized
### *Hands-on hardware crafting, modular wearable domes, and an expedition into the lost dimension.*

**English** | [简体中文](README_zh.md)

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-brightgreen.svg?style=flat-square&logo=minecraft)](https://minecraft.net/) [![NeoForge](https://img.shields.io/badge/NeoForge-21.1.250-orange.svg?style=flat-square)](https://neoforged.net/) [![GeckoLib](https://img.shields.io/badge/GeckoLib-Required-blue.svg?style=flat-square)](https://geckolib.com/) [![Curios API](https://img.shields.io/badge/Curios%20API-Required-9cf.svg?style=flat-square)](https://www.curseforge.com/minecraft/mc-mods/curios) [![License](https://img.shields.io/badge/License-GPL%20v3.0-blue.svg?style=flat-square)](https://www.gnu.org/licenses/gpl-3.0)

---

## 📖 About Mechanized

**Mechanized** is a technology and adventure mod for NeoForge Minecraft, bringing physical, in-world hardware fabrication and deep dimensional exploration together.

Forget about throwing ingredients into a 3x3 crafting grid or waiting endlessly next to automated magic-box machines. Mechanized embraces the spirit of real-world makers and hardware hackers: place components directly onto the **Machinist Bench**, saw raw circuit boards to size, hand-solder jumper wires with a soldering iron, and hammer stamped metal casings to build your personal, modular **Space Dome**.

Equip your wrist-worn dome to run on-the-fly tactical modules like jet thrust, portable quarrying, item magnets, and energy shields. As your engineering journey deepens, repair the long-abandoned spacetime gateways scattered across the Overworld and journey into **The Mechazoic** — a lost civilization where biomechanical flora and cybernetic ruins guard ancient blueprints and prime technology.

---

## ⚡ Environment & Dependencies

> [!IMPORTANT]
> **Please ensure the following dependencies are installed before launching the game, otherwise wearable bone animations and accessory slots will not function:**

| Dependency / Mod | Requirement | Target Version / Constraint | Notes |
| :--- | :---: | :---: | :--- |
| **Java** | **Runtime (JVM)** | `Java 21` | Mandatory for Minecraft 1.21.1 |
| **NeoForge** | **Required (Core)** | `21.1.250` (`[21.1.250, )`) | Minimum supported mod loader baseline |
| **GeckoLib 4** | **Required** | `4.7.4` (`[4.7, )`) | Bone animations & dynamic rendering for Space Domes |
| **Curios API** | **Required** | `9.5.1+1.21.1` (`[9.0, )`) | Dedicated accessory slots for bracelets and goggles |
| **JEI / EMI** | **Recommended** | `1.21.1` | Displays step-by-step hands-on assembly sequences |

---

## 🌟 Core Features

### 🛠️ In-World Hands-on Assembly: The Machinist Bench
* **Zero GUI, Pure In-World Crafting**: Workpieces sit directly on top of the bench surface for crystal-clear visual feedback.
* **Sequential Fabrication Steps**: Progress through realistic assembly phases — saw down substrates, solder delicate IC pins, and press housings into place with a machinist hammer.
* **Scrap Smelting & Recycling**: Missteps during sequence crafting yield metal scrap, which can be smelted back into raw ingots so you never lose your hard-earned materials.

### ⭕ Modular Wearable: The Space Dome
The centerpiece of your personal arsenal. Worn directly on the wrist, it supports swappable utility modules:
* **[Lite Space Dome]**: Handcrafted in the Overworld using copper and steel alloys, running on standard FE electrical power.
* **[Common Space Dome]**: The native Mechazoic standard. Operates on high-density Zoetium energy and offers expanded module capacity.
* **[Prime Space Dome]**: Ancient leader matrix specification. Acquired by defeating dimension rulers to unlock maximum power and module slots.
* **[Creative Space Dome]**: Creative mode exclusive with infinite energy and unrestricted slots.

Switch active modules on the fly via an intuitive radial menu hotkey.

### 💧 Alien Fuel: Zoetium & Zoetic Bloom
* **Zoetic Bloom**: Rare glowing flowers found across the Overworld. Extract their bio-energy to harvest raw liquid, withering them into dead bushes in the process.
* **Underground Zoetium Springs**: Hidden beneath surface blooms lie pools of luminous teal fluid (`#06D5F9`). Submerging yourself sparks technical inspiration, unlocking the earliest dome schematics.
* **Dual-Track Energy Progression**: Start with standard FE generators and charging pads (compatible with modern tech mod cabling), then transition into long-range **Wireless Resonance Pylons** once you tap into native Zoetium resonance.

### 🌌 Dimensional Expedition: The Mechazoic
* **Uncover Abandoned Labs**: Search the Overworld for rare underground cyber-facilities using the **Abnormal Signal Locator** (a durable, non-breaking directional scanner).
* **Gateway Restoration**: Extract burnt gateway chips, solder repairs back at base, socket them back in, and ignite the portal with a bucket of liquid Zoetium.
* **Dungeon Guardians & Blueprints**: Traverse fractured metallic biomes, confront automated sentinels, and challenge the final Ruler to claim the Prime Matrix.

### 🤖 Silicon-Based Wonders: The Mechazoa & Replicas
* **Naturally Evolved Silicon Ecology**: Entities in The Mechazoic are not cold manufactured automata, but indigenous **Mechazoa** and **Mechazoan** fauna evolved over eons. Their metal alloys and mechanical joints are as natural as bones and shells to Earth's carbon life.
* **Handcrafted Replicas & Cube Folding**: Reverse-engineer Mechazoan physiology on your Machinist Bench to handcraft your own **Mechazoan Replica** companion. When depleted of energy or recalled, it module-folds like a Transformer into a compact 1x1 block cube.
* **The Replica Cradle**: Slide folded companion cubes into base docks to recharge, perform automated repairs, and dispatch them for lightweight item logistics.

---

## 🗺️ Progression Overview

1. **Early Days**  
   Locate naturally occurring Zoetic Blooms on the surface and subterranean springs of luminous Zoetium for initial technical inspiration. Smelt structural steel and crystalline silicon in a blast furnace.

2. **The Machinist**  
   Construct the Machinist Bench. Manually saw substrates, solder delicate wiring, and assemble the Lite Space Dome. Fabricate the Smart Analyzer to reverse-engineer foundational tactical modules.

3. **The Signal**  
   Equip Smart Goggles and follow electromagnetic pulses across the underground using the Abnormal Signal Locator to uncover a long-lost abandoned cyber-laboratory.

4. **The Mechazoic**  
   Repair corrupted gateway logic chips and ignite the portal using liquid Zoetium. Cross over into The Mechazoic, study native Mechazoa ecology, handcraft folding Replica companions with Replica Cradles, and triumph over the final Ruler to ascend your wristgear into the Prime Space Dome.


---

## ⚙️ Configuration & Modpack Support

All balance parameters are thoroughly configurable and never hardcoded:
* `config/mechanized-server.toml` exposes full control over: generator output rates, scrap recycling efficiency, dome slot allocations per tier, tool energy draw, and more.
* Native custom assembly recipe support via standard DataPacks.

---

## 📜 License & Credits

* Licensed under the **GNU General Public License v3.0 (GPL v3.0)**. Modpack inclusion and adaptations are warmly welcomed!
* Thanks to GeckoLib, Curios API, and the NeoForge open-source community.
