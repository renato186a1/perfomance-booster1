package com.performancebooster

import android.app.Activity
import android.os.Bundle
import android.os.Build
import android.graphics.Color
import android.graphics.Typeface
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.*

class MainActivity : Activity() {
    private lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildUi()
    }

    private fun buildUi() {
        val scroll = ScrollView(this).apply { setBackgroundColor(Color.rgb(13, 17, 28)) }
        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 28, 24, 28)
        }

        val title = TextView(this).apply {
            text = "PERFORMANCE BOOSTER"
            textSize = 24f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        }
        val subtitle = TextView(this).apply {
            text = "Painel auxiliar • otimizações via ADB pelo PC"
            textSize = 14f
            setTextColor(Color.LTGRAY)
            setPadding(0, 8, 0, 20)
        }
        page.addView(title)
        page.addView(subtitle)

        val device = """
            Fabricante: ${Build.MANUFACTURER}
            Modelo: ${Build.MODEL}
            Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})
            Dispositivo: ${Build.DEVICE}
        """.trimIndent()
        addInfoCard(page, "INFORMAÇÕES DO APARELHO", device)

        addButton(page, "Mostrar comandos ADB de diagnóstico") {
            showOutput(
                "Execute no Prompt de Comando com o celular conectado e autorizado:\\n\\n" +
                "adb devices\\nadb shell getprop ro.product.model\\n" +
                "adb shell getprop ro.build.version.release\\n" +
                "adb shell dumpsys meminfo\\nadb shell df -h /data\\n" +
                "adb shell dumpsys thermalservice"
            )
        }
        addButton(page, "Mostrar ajuste de animações 0,5x") {
            showOutput(
                "Execute no PC:\\n\\n" +
                "adb shell settings put global window_animation_scale 0.5\\n" +
                "adb shell settings put global transition_animation_scale 0.5\\n" +
                "adb shell settings put global animator_duration_scale 0.5"
            )
        }
        addButton(page, "Mostrar comando para restaurar animações") {
            showOutput(
                "Execute no PC para restaurar o padrão 1x:\\n\\n" +
                "adb shell settings put global window_animation_scale 1\\n" +
                "adb shell settings put global transition_animation_scale 1\\n" +
                "adb shell settings put global animator_duration_scale 1"
            )
        }
        addButton(page, "Mostrar comando de compilação ART") {
            showOutput(
                "Substitua com.sua.aplicacao pelo pacote do app e execute no PC:\\n\\n" +
                "adb shell cmd package compile -m speed-profile -f com.sua.aplicacao\\n\\n" +
                "A disponibilidade e o resultado dependem da versão Android."
            )
        }
        addButton(page, "Copiar texto exibido") {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("Comandos ADB", output.text))
            Toast.makeText(this, "Texto copiado", Toast.LENGTH_SHORT).show()
        }

        output = TextView(this).apply {
            text = "Escolha uma opção acima. Os comandos são executados no computador, não diretamente pelo APK."
            textSize = 13f
            setTextColor(Color.rgb(210, 218, 230))
            setPadding(0, 20, 0, 20)
        }
        page.addView(output)

        val note = TextView(this).apply {
            text = "Este APK não usa Shizuku nem root. Para aplicar ajustes, use Android Platform Tools (ADB) no computador, com Depuração USB ativada e autorização concedida no celular."
            textSize = 12f
            setTextColor(Color.LTGRAY)
            setPadding(0, 20, 0, 8)
        }
        page.addView(note)

        scroll.addView(page)
        setContentView(scroll)
    }

    private fun addInfoCard(parent: LinearLayout, heading: String, body: String) {
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 14, 16, 14)
            setBackgroundColor(Color.rgb(28, 36, 52))
        }
        val h = TextView(this).apply {
            text = heading
            textSize = 12f
            setTextColor(Color.rgb(100, 210, 255))
            typeface = Typeface.DEFAULT_BOLD
        }
        val b = TextView(this).apply {
            text = body
            textSize = 14f
            setTextColor(Color.WHITE)
            setPadding(0, 8, 0, 0)
        }
        container.addView(h)
        container.addView(b)
        parent.addView(container, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 16 })
    }

    private fun addButton(parent: LinearLayout, label: String, action: () -> Unit) {
        val button = Button(this).apply {
            text = label
            isAllCaps = false
            setOnClickListener { action() }
        }
        parent.addView(button, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 8 })
    }

    private fun showOutput(text: String) {
        output.text = text
    }
}
