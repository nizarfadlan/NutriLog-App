package com.nutrilog.app.utils.helpers

import com.nutrilog.app.domain.model.Nutrition
import com.nutrilog.app.domain.model.NutritionOption

fun convertListToNutritionLevel(list: List<Nutrition>): Map<NutritionOption, Double> {
    return NutritionOption.entries.associateWith { option ->
        list.sumOf { nutrition ->
            when (option) {
                NutritionOption.CARBOHYDRATE -> nutrition.carbohydrate.toDouble()
                NutritionOption.PROTEIN -> nutrition.proteins.toDouble()
                NutritionOption.FAT -> nutrition.fat.toDouble()
                NutritionOption.CALORIES -> nutrition.calories.toDouble()
            }
        }
    }
}

fun convertToNutritionLevel(list: Nutrition): Map<NutritionOption, Double> {
    return NutritionOption.entries.associateWith { option ->
        when (option) {
            NutritionOption.CARBOHYDRATE -> list.carbohydrate.toDouble()
            NutritionOption.PROTEIN -> list.proteins.toDouble()
            NutritionOption.FAT -> list.fat.toDouble()
            NutritionOption.CALORIES -> list.calories.toDouble()
        }
    }
}
