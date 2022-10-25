package com.example.ra.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel()
{
    private val _cliente= mutableStateOf("")

    private val _auto= mutableStateOf("Null")

    private val _precio= mutableStateOf(0.0)

    private val _enganche= mutableStateOf(0.0)

    private val _financiamiento= mutableStateOf(0)

    private val _porcentaje= mutableStateOf(0.0)

    fun ConocerNombre(name:String)
    {
            _cliente.value = name
    }

    fun ConocerMarca(Marca:String,valor:Double){
        _auto.value = Marca
        _precio.value = valor
    }

    fun ConocerEnganche(porcentajeEnganche:Double){
        _enganche.value = porcentajeEnganche
    }

    fun ConocerFinanciamiento(fecha:Int,porcentajeFinanciamiento:Double){
        _financiamiento.value = fecha
        _porcentaje.value = porcentajeFinanciamiento
    }


    fun reset(){
        _cliente.value = ""
        _auto.value = "Null"
        _precio.value = 0.0
        _enganche.value = 0.0
        _financiamiento.value = 0
        _porcentaje.value = 0.0
    }

    fun conocerDatos():String{
        var conocerEnganche = _precio.value * (_enganche.value/100)
        var MontoFinanciar = _precio.value - conocerEnganche
        var interesAnio = MontoFinanciar * (_porcentaje.value/100)
        var PagoInteres = interesAnio * _financiamiento.value
        var MontoFinanciarInteres = MontoFinanciar + PagoInteres
        var PagoMensual = MontoFinanciarInteres / (_financiamiento.value * 12)
        var CostoTotal=  conocerEnganche + MontoFinanciarInteres

        return "Cliente: ${_cliente.value}\n" +
                "Vehiculo: ${_auto.value} \$ ${_precio.value}\n" +
                "Enganche: (${_enganche.value}%) de ${conocerEnganche}\n" +
                "Monto a financiar: $${MontoFinanciar}\n" +
                "Financiamiento a: ${_financiamiento.value} años, tasa del ${_porcentaje.value}%\n" +
                "Monto de Intereses: por ${_financiamiento.value} años = $${PagoInteres}\n" +
                "Monto a financiar + interés: = $${MontoFinanciar} + $${PagoInteres} = $${MontoFinanciarInteres}\n" +
                "Pagos mensuales por: $${PagoMensual}\n" +
                "Costo total (Enganche+Financiamiento): $${conocerEnganche} + $${MontoFinanciarInteres}= $${CostoTotal}"
    }


}