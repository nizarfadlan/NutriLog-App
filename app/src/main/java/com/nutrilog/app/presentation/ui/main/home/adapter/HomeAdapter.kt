package com.nutrilog.app.presentation.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nutrilog.app.R
import com.nutrilog.app.databinding.NutritionCardBinding
import com.nutrilog.app.domain.model.NutritionLevel
import com.nutrilog.app.domain.model.NutritionOption

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.NutritionDataViewHolder>() {
    private var nutritionDataList: List<Pair<NutritionOption, Double>> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NutritionDataViewHolder {
        val binding =
            NutritionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionDataViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = nutritionDataList.size

    override fun onBindViewHolder(
        holder: NutritionDataViewHolder,
        position: Int,
    ) {
        val (nutritionOption, amount) = nutritionDataList[position]
        holder.bind(nutritionOption, amount)
    }

    fun setNutritionData(data: Map<NutritionOption, Double>) {
        val newList = data.toList()
        val diffResult = DiffUtil.calculateDiff(NutritionDiffCallback(nutritionDataList, newList))
        nutritionDataList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NutritionDataViewHolder(
        val binding: NutritionCardBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            nutritionOption: NutritionOption,
            amount: Double,
        ) {
            with(binding) {
                nutritionTypeTV.text = nutritionOption.label
                val nutritionLevel = determineNutritionLevel(amount)
                nutritionLevelTV.text = nutritionLevel.label
                totalNutritionTV.text =
                    context.getString(
                        R.string.label_amount_of_nutrition,
                        amount.toString(),
                    )

                setBgCard(nutritionLevel)
            }
        }

        private fun determineNutritionLevel(amount: Double): NutritionLevel {
            return when {
                amount >= 45 -> NutritionLevel.CLOSE
                amount > 10 && amount < 45 -> NutritionLevel.OPTIMAL
                else -> NutritionLevel.DEFICIENT
            }
        }

        private fun setBgCard(nutritionLevel: NutritionLevel) {
            val drawable =
                when (nutritionLevel) {
                    NutritionLevel.OPTIMAL ->
                        ContextCompat.getDrawable(
                            binding.cardLayoutOne.context,
                            R.drawable.bg_card_2,
                        )

                    NutritionLevel.CLOSE ->
                        ContextCompat.getDrawable(
                            binding.cardLayoutOne.context,
                            R.drawable.bg_card_3,
                        )

                    NutritionLevel.DEFICIENT ->
                        ContextCompat.getDrawable(
                            binding.cardLayoutOne.context,
                            R.drawable.bg_card_1,
                        )
                }
            binding.cardLayoutOne.background = drawable
        }
    }

    private class NutritionDiffCallback(
        private val oldList: List<Pair<NutritionOption, Double>>,
        private val newList: List<Pair<NutritionOption, Double>>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int,
        ): Boolean {
            return oldList[oldItemPosition].first == newList[newItemPosition].first
        }

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int,
        ): Boolean {
            return oldList[oldItemPosition].second == newList[newItemPosition].second
        }
    }
}
