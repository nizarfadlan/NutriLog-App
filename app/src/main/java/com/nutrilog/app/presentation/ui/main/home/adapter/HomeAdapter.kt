package com.nutrilog.app.presentation.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nutrilog.app.R
import com.nutrilog.app.databinding.NutritionCardBinding
import com.nutrilog.app.domain.model.ActiveLevel
import com.nutrilog.app.domain.model.NutritionLevel
import com.nutrilog.app.domain.model.NutritionOption
import com.nutrilog.app.utils.helpers.formatNutritionAmount
import com.nutrilog.app.utils.helpers.getNutritionLimit

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.NutritionDataViewHolder>() {
    private var nutritionDataList: List<Pair<NutritionOption, Double>> = listOf()
    private var userAge: Int = 0
    private lateinit var userGender: String
    private lateinit var userActiveLevel: ActiveLevel

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

    fun setUserData(
        age: Int,
        gender: String,
    ) {
        userAge = age
        userGender = gender
    }

    fun setActiveLevel(activeLevel: ActiveLevel) {
        userActiveLevel = activeLevel
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
                val nutritionLimit = getNutritionLimit(userAge, userActiveLevel, nutritionOption)
                val nutritionLevel = determineNutritionLevel(amount, nutritionLimit)
                nutritionLevelTV.text = nutritionLevel.label

                totalNutritionTV.text =
                    formatNutritionAmount(context, amount, nutritionOption.label == "Calories")

                setBgCard(nutritionLevel)
            }
        }

        private fun determineNutritionLevel(
            amount: Double,
            limit: List<Double>,
        ): NutritionLevel {
            return when {
                amount >= limit[1] && amount < limit[2] -> NutritionLevel.CLOSE
                amount > limit[0] && amount < limit[1] -> NutritionLevel.OPTIMAL
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
