# PollutedWorld - Minecraft Plugin

**PollutedWorld** to plugin do Minecrafta 1.21+, który dodaje zaawansowane funkcje związane ze skażeniem i miksturami antyradiacyjnymi w świecie Minecraft.

---

## Funkcje

- Skażone strefy (Radiation Zones)  
- Obrażenia w skażonych terenach  
- Mikstury antyradiacyjne z **zielonym BossBarem** pokazującym czas działania  
- Strefy bezpieczne obejmujące **całą wysokość**  
- BossBary dla skażenia i mikstur antyradiacyjnych  
- Crafting mikstury antyradiacyjnej na **stole alchemicznym**  

---

## Inspiracja

Plugin był częściowo inspirowany projektem [CraftserveRadiation](https://github.com/Craftserve/CraftserveRadiation).  

W PollutedWorld zostały dodane między innymi:  

- Zielone bossbary dla mikstur antyradiacyjnych  
- Możliwość odnawiania czasu działania mikstury  
- Strefy bezpieczne obejmujące całą wysokość świata  
- Kompatybilność z Minecraft 1.21+  
- Uproszczenie kodu i poprawa stabilności  

---

## Komendy

- `/safezone` – tworzenie strefy bezpiecznej (cała wysokość)  
- Crafting mikstury antyradiacyjnej – użycie **stole alchemicznego**  

> Komendy związane z logami bloków zostały usunięte i plugin nie zapisuje logów.  

---

## Crafting mikstury antyradiacyjnej

- Wykonuje się na stole alchemicznym  
- Składniki:  
  - **SLIME_BLOCK** (możesz zmienić w config)  
  - **GLOWSTONE_DUST**  
  - **FERMENTED_SPIDER_EYE**  
  - **GLASS_BOTTLE**  
- Efekt: Zielony bossbar z czasem działania mikstury  
- Można wypić kolejną miksturę po zakończeniu poprzedniej – czas się odnawia, nie tworzy nowego bossbara  

---

## Instalacja

1. Skompiluj projekt przy użyciu Maven lub Gradle  
2. Wrzuc plik `PollutedWorld.jar` do folderu `plugins` serwera Minecraft  
3. Uruchom serwer  
4. Plugin automatycznie stworzy `config.yml` przy pierwszym starcie  

---

## Konfiguracja

Wszystkie ustawienia są dostępne w `config.yml`:

- Czas działania mikstury antyradiacyjnej (w sekundach)  
- Obrażenia od radiacji  
- Kształt i skład mikstury  

---

## Obsługa i wskazówki

- Bossbar skażenia jest **żółty**, mikstury antyradiacyjne mają **zielony**  
- Strefy bezpieczne nie pozwalają na obrażenia w środku  
- Mikstury działają jak zwykłe mikstury Minecraft, więc można je pić wielokrotnie  

---

## Licencja i uwagi

- Projekt jest **autorskim pluginem**, częściowo inspirowanym projektem [CraftserveRadiation](https://github.com/Craftserve/CraftserveRadiation)  
- Możesz używać pluginu na własnym serwerze  
- Kod źródłowy jest dostępny w repozytorium GitHub  
