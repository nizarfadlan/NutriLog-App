package com.nutrilog.app.presentation.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nutrilog.app.R
import com.nutrilog.app.databinding.NutritionCardBinding
import com.nutrilog.app.domain.model.Nutrition
import com.nutrilog.app.domain.model.NutritionLevel
import com.nutrilog.app.domain.model.NutritionOption
import timber.log.Timber

class HomeAdapter(private val listNutritionData: List<Nutrition>) : RecyclerView.Adapter<HomeAdapter.NutritionDataViewHolder>() {

//    private var listNutritionData: List<Nutrition> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionDataViewHolder {
        val binding =
            NutritionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionDataViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = listNutritionData.size

    override fun onBindViewHolder(holder: NutritionDataViewHolder, position: Int) {
        holder.bind(listNutritionData[position])
    }

//    fun setNutritionData(data: List<Nutrition>) {
//        val diffResult = DiffUtil.calculateDiff(
//            NutritionDiffCallback(listNutritionData, data)
//        )
//        listNutritionData = data
//        diffResult.dispatchUpdatesTo(this)
//    }

    inner class NutritionDataViewHolder(
        val binding: NutritionCardBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nutrition: Nutrition) {
            with(binding) {

                nutritionTypeTV.text = NutritionOption.CARBOHYDRATE.label
                // Make a function to calculate and define the nutrition level
                nutritionLevelTV.text = NutritionLevel.OPTIMAL.label
                totalNutritionTV.text = context.getString(
                    R.string.label_amount_of_nutrition,
                    nutrition.proteins.toString()
                )
                // i think if it's possible it's better to add the layout id on the parameter rather than indicator position
                setBgCard(0, 1)

                nutritionTypeTv2.text = NutritionOption.PROTEIN.label
                nutritionLevelTv2.text = NutritionLevel.CLOSE.label
                totalNutritionTv2.text = context.getString(
                    R.string.label_amount_of_nutrition,
                    nutrition.proteins.toString()
                )
                setBgCard(1, 2)

                nutritionTypeTv3.text = NutritionOption.FAT.label
                nutritionLevelTv3.text = NutritionLevel.DEFICIENT.label
                totalNutritionTv3.text = context.getString(
                    R.string.label_amount_of_nutrition,
                    nutrition.proteins.toString()
                )
                setBgCard(2, 3)

                nutritionTypeTv4.text = NutritionOption.CALORIES.label
                nutritionLevelTv4.text = NutritionLevel.CLOSE.label
                totalNutritionTv4.text = context.getString(
                    R.string.label_amount_of_nutrition,
                    nutrition.proteins.toString()
                )
                setBgCard(1, 4)

//                root.setOnClickListener {
//                    onItemClickCallback(nutrition.id)
//                }
            }
        }

        /**
         *
         */
        fun setBgCard(position: Int, cardLayout: Int) {
            val drawable = when (position) {
                0 -> {
                    ContextCompat.getDrawable(binding.cardLayoutOne.context, R.drawable.bg_card_2)
                }
                1 -> {
                    ContextCompat.getDrawable(binding.cardLayoutOne.context, R.drawable.bg_card_3)
                }
                2 -> {
                    ContextCompat.getDrawable(binding.cardLayoutOne.context, R.drawable.bg_card_1)
                }
                else -> {
                    ContextCompat.getDrawable(binding.cardLayoutOne.context, R.drawable.bg_card_2)
                }
            }
            when(cardLayout) {
                1 -> binding.cardLayoutOne.background = drawable
                2 -> binding.cardLayoutTwo.background = drawable
                3 -> binding.cardLayoutThree.background = drawable
                4 -> binding.cardLayoutFour.background = drawable
            }
        }
    }



    private class NutritionDiffCallback(
        private val oldList: List<Nutrition>,
        private val newList: List<Nutrition>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}