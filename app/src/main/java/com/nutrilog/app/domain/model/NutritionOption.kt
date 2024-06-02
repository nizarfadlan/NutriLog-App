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
    DEFICIENT("Deficient")
}