package com.nutrilog.app.domain.model

enum class NutritionOption(val label: String) {
    CARBOHYDRATE("Carbohydrate"),
    PROTEIN("Protein"),
    FAT("Fat"),
    CALORIES("Calories")
}

enum class NutritionLevel(val label: String) {
    OPTIMAL("Optimal"),
    CLOSE("Close to limit"),
    DEFICIENT("Deficient"),
    OVER("Excessively high")
}

enum class ProteinSedentaryLimit(val proteinLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit > 19.0
    CHILDREN(listOf(13.0, 15.0, 19.0)),
    ADOLESCENTS(listOf(34.0, 45.0, 52.0)),
    ADULTS(listOf(45.0, 58.0, 66.0)),
    MATURE(listOf(45.0, 58.0, 66.0)),
    ELDER(listOf(45.0, 58.0, 66.0))
}

enum class ProteinModerateLimit(val proteinLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(15.0, 17.5, 21.0)),
    ADOLESCENTS(listOf(38.0, 49.0, 57.0)),
    ADULTS(listOf(49.0, 63.0, 72.0)),
    MATURE(listOf(49.0, 63.0, 72.0)),
    ELDER(listOf(49.0, 63.0, 72.0))
}

enum class ProteinActiveLimit(val proteinLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(17.0, 20.0, 23.0)),
    ADOLESCENTS(listOf(42.0, 53.0, 62.0)),
    ADULTS(listOf(53.0, 68.0, 78.0)),
    MATURE(listOf(53.0, 68.0, 78.0)),
    ELDER(listOf(53.0, 68.0, 78.0))
}

enum class FatSedentaryLimit(val fatLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(30.0, 35.0, 40.0)),
    ADOLESCENTS(listOf(25.0, 30.0, 35.0)),
    ADULTS(listOf(20.0, 25.0, 30.0)),
    MATURE(listOf(20.0, 25.0, 30.0)),
    ELDER(listOf(20.0, 25.0, 30.0))
}

enum class FatModerateLimit(val fatLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(27.0, 32.0, 37.0)),
    ADOLESCENTS(listOf(23.0, 28.0, 32.0)),
    ADULTS(listOf(18.0, 23.0, 28.0)),
    MATURE(listOf(18.0, 23.0, 28.0)),
    ELDER(listOf(18.0, 23.0, 28.0))
}

enum class FatActiveLimit(val fatLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(24.0, 29.0, 34.0)),
    ADOLESCENTS(listOf(21.0, 26.0, 29.0)),
    ADULTS(listOf(16.0, 21.0, 26.0)),
    MATURE(listOf(16.0, 21.0, 26.0)),
    ELDER(listOf(16.0, 21.0, 26.0))
}

enum class CarbSedentaryLimit(val carbLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(50.0, 55.0, 60.0)),
    ADOLESCENTS(listOf(45.0, 50.0, 55.0)),
    ADULTS(listOf(40.0, 45.0, 50.0)),
    MATURE(listOf(40.0, 45.0, 50.0)),
    ELDER(listOf(40.0, 45.0, 50.0))
}

enum class CarbModerateLimit(val carbLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(55.0, 60.0, 65.0)),
    ADOLESCENTS(listOf(50.0, 55.0, 60.0)),
    ADULTS(listOf(45.0, 50.0, 55.0)),
    MATURE(listOf(45.0, 50.0, 55.0)),
    ELDER(listOf(45.0, 50.0, 55.0))
}

enum class CarbActiveLimit(val carbLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(60.0, 65.0, 70.0)),
    ADOLESCENTS(listOf(55.0, 60.0, 65.0)),
    ADULTS(listOf(50.0, 55.0, 60.0)),
    MATURE(listOf(50.0, 55.0, 60.0)),
    ELDER(listOf(50.0, 55.0, 60.0))
}

enum class CalorieSedentaryLimit(val calorieLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(1200.0, 1400.0, 1600.0)),
    ADOLESCENTS(listOf(1800.0, 2000.0, 2200.0)),
    ADULTS(listOf(1800.0, 2000.0, 2200.0)),
    MATURE(listOf(1600.0, 1800.0, 2000.0)),
    ELDER(listOf(1400.0, 1600.0, 1800.0))
}

enum class CalorieModerateLimit(val calorieLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(1400.0, 1600.0, 1800.0)),
    ADOLESCENTS(listOf(2000.0, 2200.0, 2400.0)),
    ADULTS(listOf(2000.0, 2200.0, 2400.0)),
    MATURE(listOf(1800.0, 2000.0, 2200.0)),
    ELDER(listOf(1600.0, 1800.0, 2000.0))
}

enum class CalorieActiveLimit(val calorieLimit: List<Double>) {
    // minimum, optimal, almost limit, over-limit
    CHILDREN(listOf(1600.0, 1800.0, 2000.0)),
    ADOLESCENTS(listOf(2200.0, 2400.0, 2600.0)),
    ADULTS(listOf(2200.0, 2400.0, 2600.0)),
    MATURE(listOf(2000.0, 2200.0, 2400.0)),
    ELDER(listOf(1800.0, 2000.0, 2200.0))
}