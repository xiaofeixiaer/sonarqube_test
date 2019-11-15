package com.hw.tools.route.trace

import org.opennms.netmgt.icmp.EchoPacket
import org.opennms.netmgt.icmp.PingResponseCallback
import org.opennms.netmgt.icmp.PingerFactoryImpl
import java.net.InetAddress

fun main() {
    val address = InetAddress.getByName("www.baidu.com")
    val pinger = PingerFactoryImpl().instance

    val number = pinger.ping(
            address,
            1000,
            1,
            3,
            TDPingResponseCallback()
    )
    println(number)
}

class TDPingResponseCallback : PingResponseCallback {
    override fun handleError(address: InetAddress?, request: EchoPacket?, t: Throwable?) {
        t?.printStackTrace()
    }

    override fun handleTimeout(address: InetAddress?, request: EchoPacket?) {
        println("TimeOut")
    }

    override fun handleResponse(address: InetAddress?, response: EchoPacket?) {
        println(response)
    }
}