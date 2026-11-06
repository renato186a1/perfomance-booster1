# Performance Booster — ADB Only

Este projeto é um painel auxiliar Android e scripts ADB para Windows. O APK mostra informações do dispositivo e apresenta comandos para copiar/executar no computador. **O APK não recebe a identidade/permissões ADB do computador** e não executa comandos ADB remotamente por conta própria.

## Compilação GitHub Actions
- JDK 17
- Gradle 8.9
- Android Gradle Plugin 8.7.3
- Kotlin 2.0.21

Envie os arquivos para o repositório, execute **Actions → Android CI - Performance Booster** e baixe `Performance-Booster-debug`.

## Autorizar ADB no Android
1. Instale Android SDK Platform Tools no PC.
2. No Android, ative Opções do desenvolvedor → Depuração USB.
3. Conecte o cabo USB e aceite a janela RSA “Permitir depuração USB?”.
4. No PC, rode:

```bat
adb devices
```

O dispositivo deve aparecer como `device`, não `unauthorized`.

A autorização RSA permite que **o computador** use ADB. Não existe um `pm grant` universal para conceder ao APK a identidade `shell`/permissões ADB. `pm grant` concede apenas permissões Android de runtime que o aplicativo declarou; não transforma o aplicativo em ADB ou root.

## Comandos de exemplo no PC

```bat
adb shell getprop ro.product.model
adb shell dumpsys meminfo
adb shell df -h /data

adb shell settings put global window_animation_scale 0.5
adb shell settings put global transition_animation_scale 0.5
adb shell settings put global animator_duration_scale 0.5
```

Restaurar animações:

```bat
adb shell settings put global window_animation_scale 1
adb shell settings put global transition_animation_scale 1
adb shell settings put global animator_duration_scale 1
```

Para rodar os scripts da pasta `adb`, abra o Prompt de Comando na pasta `platform-tools` ou adicione `adb.exe` ao PATH.

## Limites
Não há Shizuku, root, overclock ou execução ADB dentro do APK. Os comandos ADB são executados no PC conectado. As escalas de animação mudam a fluidez visual da interface, não aumentam diretamente a potência de CPU/GPU.
