package br.com.alura.financask.ui

import android.support.v4.content.ContextCompat
import android.view.View
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import  android.content.Context
import java.math.BigDecimal

class ResumoView(private val context: Context,
        private val view : View, transacoes : List<Transacao>) {


    private val resumo : Resumo  = Resumo(transacoes)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)

    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita){
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }


    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa
        with( view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }


    private fun adicionaTotal(){
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total){
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
         if (valor >= BigDecimal.ZERO) {
            return corReceita
        }
            return corDespesa
        }

    fun atualiza() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }
}