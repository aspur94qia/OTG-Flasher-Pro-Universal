package com.otg.flasherpro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.TextView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    // Data dinamis, bukan hardcode Redmi
    data class DeviceInfo(
        var model: String = "Menunggu perangkat...",
        var product: String = "--",
        var bootloader: String = "--",
        var slot: String = "--",
        var maxDownload: String = "--",
        var status: String = "Tidak terhubung",
        var mode: String = ""
    )

    private var currentDevice = DeviceInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        updateUI(currentDevice) // awalnya kosong, bukan Redmi
        setupBottomNav()
        setupActions()
    }

    private fun setupUI() {
        // Update UI awal: bukan Redmi, tapi menunggu
        currentDevice = DeviceInfo(
            model = "Menunggu perangkat OTG...",
            product = "--",
            bootloader = "--",
            slot = "--",
            maxDownload = "--",
            status = "Tidak terhubung",
            mode = ""
        )
    }

    fun onDeviceDetected(model: String, product: String, bootloader: String, slot: String, maxDl: String, mode: String) {
        // Ini dipanggil pas OTG deteksi HP apapun - BISA SAMSUNG, XIAOMI, OPPO, dll
        currentDevice = DeviceInfo(
            model = model, // otomatis dari HP yang dicolok
            product = product,
            bootloader = bootloader,
            slot = slot,
            maxDownload = maxDl,
            status = "Terhubung lewat OTG",
            mode = mode
        )
        updateUI(currentDevice)
    }

    private fun updateUI(device: DeviceInfo) {
        // findViewById sesuai layout kamu yang di gambar
        try {
            findViewById<TextView>(R.id.deviceModel)?.text = device.model
            findViewById<TextView>(R.id.deviceProduct)?.text = device.product
            findViewById<TextView>(R.id.deviceBootloader)?.text = device.bootloader
            findViewById<TextView>(R.id.deviceSlot)?.text = device.slot
            findViewById<TextView>(R.id.deviceMaxDl)?.text = device.maxDownload
            findViewById<TextView>(R.id.deviceStatus)?.text = device.status
            findViewById<TextView>(R.id.deviceMode)?.text = device.mode
        } catch (e: Exception) {}
    }

    private fun setupBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav?.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_perangkat -> true
                R.id.nav_partisi -> true
                R.id.nav_urutan -> true
                R.id.nav_log -> true
                else -> false
            }
        }
    }

    private fun setupActions() {
        // Aksi cepat kayak di gambar kamu
        findViewById<CardView>(R.id.btnBacaPartisi)?.setOnClickListener { /* baca partisi */ }
        findViewById<CardView>(R.id.btnCariFile)?.setOnClickListener { /* cari file */ }
        findViewById<CardView>(R.id.btnBackup)?.setOnClickListener { /* backup */ }
        findViewById<CardView>(R.id.btnRencana)?.setOnClickListener { /* rencana */ }
        findViewById<LinearLayout>(R.id.btnMulaiFlash)?.setOnClickListener { /* mulai flash */ }
    }
}
