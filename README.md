# Performance Booster — ADB Edition

Aplicativo Android auxiliar, sem Shizuku e sem root, com informações do aparelho e comandos ADB copiáveis. Os ajustes de sistema são executados pelo computador usando Android Platform Tools.

## Compilar
1. Envie o projeto para um repositório GitHub.
2. Abra Actions e execute `Android CI - Performance Booster`.
3. Baixe o artefato `Performance-Booster-debug`.

O workflow usa JDK 17, Gradle 8.9 e Android Gradle Plugin 8.7.3.

## Usar ADB no Windows
1. Instale Android Platform Tools: https://developer.android.com/tools/releases/platform-tools
2. Ative Opções do desenvolvedor > Depuração USB.
3. Conecte o aparelho ao PC e aceite a autorização RSA.
4. Abra o Prompt de Comando na pasta `platform-tools`.
5. Execute `adb devices` e confirme que o dispositivo aparece como `device`.
6. Execute `adb/adb-optimize-windows.bat`.
7. Para restaurar as animações, execute `adb/restore-defaults-windows.bat`.

## Limites
O APK é um painel auxiliar e não executa comandos ADB por conta própria. A execução ocorre no PC, através do cabo USB/ADB. Escalas de animação alteram a fluidez visual da interface, não a potência real da CPU/GPU. Não há overclock, encerramento agressivo de processos ou promessa de aumento universal de FPS.
