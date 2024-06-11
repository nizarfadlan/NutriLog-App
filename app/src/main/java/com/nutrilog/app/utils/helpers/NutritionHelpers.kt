package com.nutrilog.app.utils.helpers

import com.nutrilog.app.domain.model.ActiveLevel
import com.nutrilog.app.domain.model.CalorieActiveLimit
import com.nutrilog.app.domain.model.CalorieModerateLimit
import com.nutrilog.app.domain.model.CalorieSedentaryLimit
import com.nutrilog.app.domain.model.CarbActiveLimit
import com.nutrilog.app.domain.model.CarbModerateLimit
import com.nutrilog.app.domain.model.CarbSedentaryLimit
import com.nutrilog.app.domain.model.FatActiveLimit
import com.nutrilog.app.domain.model.FatModerateLimit
import com.nutrilog.app.domain.model.FatSedentaryLimit
import com.nutrilog.app.domain.model.Nutrition
import com.nutrilog.app.domain.model.NutritionOption
import com.nutrilog.app.domain.model.ProteinActiveLimit
import com.nutrilog.app.domain.model.ProteinModerateLimit
import com.nutrilog.app.domain.model.ProteinSedentaryLimit

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

fun getNutritionLimit(age: Int, activeLvl: ActiveLevel, type: NutritionOption): List<Double> {
    return when(age) {
        in 2..8 -> {
            when(type) {
                NutritionOption.PROTEIN -> {
                    when(activeLvl) {
                        ActiveLevel.ACTIVE -> ProteinActiveLimit.CHILDREN.proteinLimit
                        ActiveLevel.MODERATELY -> ProteinModerateLimit.CHILDREN.proteinLimit
                        else -> ProteinSedentaryLimit.CHILDREN.proteinLimit
                    }
                }
                NutritionOption.FAT -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> FatActiveLimit.CHILDREN.fatLimit
                        ActiveLevel.MODERATELY -> FatModerateLimit.CHILDREN.fatLimit
                        else -> FatSedentaryLimit.CHILDREN.fatLimit
                    }
                }
                NutritionOption.CARBOHYDRATE -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CarbActiveLimit.CHILDREN.carbLimit
                        ActiveLevel.MODERATELY -> CarbModerateLimit.CHILDREN.carbLimit
                        else -> CarbSedentaryLimit.CHILDREN.carbLimit
                    }
                }
                NutritionOption.CALORIES -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CalorieActiveLimit.CHILDREN.calorieLimit
                        ActiveLevel.MODERATELY -> CalorieModerateLimit.CHILDREN.calorieLimit
                        else -> CalorieSedentaryLimit.CHILDREN.calorieLimit
                    }
                }
            }
        }
        in 9 .. 18 -> {
            when(type) {
                NutritionOption.PROTEIN -> {
                    when(activeLvl) {
                        ActiveLevel.ACTIVE -> ProteinActiveLimit.ADOLESCENTS.proteinLimit
                        ActiveLevel.MODERATELY -> ProteinModerateLimit.ADOLESCENTS.proteinLimit
                        else -> ProteinSedentaryLimit.ADOLESCENTS.proteinLimit
                    }
                }
                NutritionOption.FAT -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> FatActiveLimit.ADOLESCENTS.fatLimit
                        ActiveLevel.MODERATELY -> FatModerateLimit.ADOLESCENTS.fatLimit
                        else -> FatSedentaryLimit.ADOLESCENTS.fatLimit
                    }
                }
                NutritionOption.CARBOHYDRATE -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CarbActiveLimit.ADOLESCENTS.carbLimit
                        ActiveLevel.MODERATELY -> CarbModerateLimit.ADOLESCENTS.carbLimit
                        else -> CarbSedentaryLimit.ADOLESCENTS.carbLimit
                    }
                }
                NutritionOption.CALORIES -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CalorieActiveLimit.ADOLESCENTS.calorieLimit
                        ActiveLevel.MODERATELY -> CalorieModerateLimit.ADOLESCENTS.calorieLimit
                        else -> CalorieSedentaryLimit.ADOLESCENTS.calorieLimit
                    }
                }
            }
        }
        in 19..30 -> {
            when(type) {
                NutritionOption.PROTEIN -> {
                    when(activeLvl) {
                        ActiveLevel.ACTIVE -> ProteinActiveLimit.ADULTS.proteinLimit
                        ActiveLevel.MODERATELY -> ProteinModerateLimit.ADULTS.proteinLimit
                        else -> ProteinSedentaryLimit.ADULTS.proteinLimit
                    }
                }
                NutritionOption.FAT -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> FatActiveLimit.ADULTS.fatLimit
                        ActiveLevel.MODERATELY -> FatModerateLimit.ADULTS.fatLimit
                        else -> FatSedentaryLimit.ADULTS.fatLimit
                    }
                }
                NutritionOption.CARBOHYDRATE -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CarbActiveLimit.ADULTS.carbLimit
                        ActiveLevel.MODERATELY -> CarbModerateLimit.ADULTS.carbLimit
                        else -> CarbSedentaryLimit.ADULTS.carbLimit
                    }
                }
                NutritionOption.CALORIES -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CalorieActiveLimit.ADULTS.calorieLimit
                        ActiveLevel.MODERATELY -> CalorieModerateLimit.ADULTS.calorieLimit
                        else -> CalorieSedentaryLimit.ADULTS.calorieLimit
                    }
                }
            }
        }
        in 31..50 -> {
            when(type) {
                NutritionOption.PROTEIN -> {
                    when(activeLvl) {
                        ActiveLevel.ACTIVE -> ProteinActiveLimit.MATURE.proteinLimit
                        ActiveLevel.MODERATELY -> ProteinModerateLimit.MATURE.proteinLimit
                        else -> ProteinSedentaryLimit.MATURE.proteinLimit
                    }
                }
                NutritionOption.FAT -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> FatActiveLimit.MATURE.fatLimit
                        ActiveLevel.MODERATELY -> FatModerateLimit.MATURE.fatLimit
                        else -> FatSedentaryLimit.MATURE.fatLimit
                    }
                }
                NutritionOption.CARBOHYDRATE -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CarbActiveLimit.MATURE.carbLimit
                        ActiveLevel.MODERATELY -> CarbModerateLimit.MATURE.carbLimit
                        else -> CarbSedentaryLimit.MATURE.carbLimit
                    }
                }
                NutritionOption.CALORIES -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CalorieActiveLimit.MATURE.calorieLimit
                        ActiveLevel.MODERATELY -> CalorieModerateLimit.MATURE.calorieLimit
                        else -> CalorieSedentaryLimit.MATURE.calorieLimit
                    }
                }
            }
        }
        else -> {
            when(type) {
                NutritionOption.PROTEIN -> {
                    when(activeLvl) {
                        ActiveLevel.ACTIVE -> ProteinActiveLimit.ELDER.proteinLimit
                        ActiveLevel.MODERATELY -> ProteinModerateLimit.ELDER.proteinLimit
                        else -> ProteinSedentaryLimit.ELDER.proteinLimit
                    }
                }
                NutritionOption.FAT -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> FatActiveLimit.ELDER.fatLimit
                        ActiveLevel.MODERATELY -> FatModerateLimit.ELDER.fatLimit
                        else -> FatSedentaryLimit.ELDER.fatLimit
                    }
                }
                NutritionOption.CARBOHYDRATE -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CarbActiveLimit.ELDER.carbLimit
                        ActiveLevel.MODERATELY -> CarbModerateLimit.ELDER.carbLimit
                        else -> CarbSedentaryLimit.ELDER.carbLimit
                    }
                }
                NutritionOption.CALORIES -> {
                    when (activeLvl) {
                        ActiveLevel.ACTIVE -> CalorieActiveLimit.ELDER.calorieLimit
                        ActiveLevel.MODERATELY -> CalorieModerateLimit.ELDER.calorieLimit
                        else -> CalorieSedentaryLimit.ELDER.calorieLimit
                    }
                }
            }
        }
    }
}