package com.example.conversordemedida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.conversordemedida.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoConverter.setOnClickListener { calcularConversao() }
    }

    private fun calcularConversao() {

        // Acessar a metida a ser convertida
        val strInserida = binding.medidaBase.text.toString()
        val valorInserido = strInserida.toDoubleOrNull()
        if (valorInserido == null) {
            binding.medidaConvertida.text = ""
            return
        }

        // Fazer a conversão
        val medidaConvertida: Double

        when (binding.unidadeMedida.checkedRadioButtonId) {
            R.id.atual_mm -> {
                medidaConvertida = when (binding.opcoesDeUnidades.checkedRadioButtonId) {
                    R.id.unidade_mm -> {
                        valorInserido
                    }
                    R.id.unidade_cm -> {
                        valorInserido / 10
                    }
                    R.id.unidade_m -> {
                        valorInserido / 1000
                    }
                    else -> {
                        valorInserido / 1000000
                    }
                }
            }
            R.id.atual_cm -> {
                medidaConvertida = when (binding.opcoesDeUnidades.checkedRadioButtonId) {
                    R.id.unidade_mm -> {
                        valorInserido * 10
                    }
                    R.id.unidade_cm -> {
                        valorInserido
                    }
                    R.id.unidade_m -> {
                        valorInserido / 100
                    }
                    else -> valorInserido / 100000
                }
            }
            R.id.atual_m -> {
                medidaConvertida = when (binding.opcoesDeUnidades.checkedRadioButtonId) {
                    R.id.unidade_mm -> {
                        valorInserido * 1000
                    }
                    R.id.unidade_cm -> {
                        valorInserido * 100
                    }
                    R.id.unidade_m -> {
                        valorInserido
                    }
                    else -> valorInserido / 1000
                }
            }
            else -> {
                medidaConvertida = when (binding.opcoesDeUnidades.checkedRadioButtonId) {
                    R.id.unidade_mm -> {
                        valorInserido * 1000000
                    }
                    R.id.unidade_cm -> {
                        valorInserido * 100000
                    }
                    R.id.unidade_m -> {
                        valorInserido * 1000
                    }
                    else -> valorInserido
                }
            }
        }

        // Exibir valor convertido
        "%.2f".format(medidaConvertida).also { binding.medidaConvertida.text = it }
    }
}