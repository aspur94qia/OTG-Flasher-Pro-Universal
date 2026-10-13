package com.otg.flasherpro.core
import android.hardware.usb.*

class FastbootUniversal(private val conn: UsbDeviceConnection, private val epIn: UsbEndpoint, private val epOut: UsbEndpoint) {
 fun cmd(cmd: String): String {
  val out = cmd.toByteArray()
  conn.bulkTransfer(epOut, out, out.size, 5000)
  val buf = ByteArray(1024)
  val len = conn.bulkTransfer(epIn, buf, buf.size, 5000)
  return if(len>0) String(buf,0,len) else ""
 }
 fun getProduct(): String {
  val r = cmd("getvar:product")
  return r.replace("OKAY","").replace("INFO","").replace("\n","").trim().ifEmpty { "unknown" }
 }
 fun getAll(): String = cmd("getvar:all")
}
