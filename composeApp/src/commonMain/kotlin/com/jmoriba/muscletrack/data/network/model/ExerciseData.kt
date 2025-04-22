import com.jmoriba.muscletrack.data.network.model.ExerciseSetData


data class ExerciseData (
    val name: String,
    val sets: List<ExerciseSetData> = emptyList()
) {
    companion object {
        fun defaultExercise(): ExerciseData {
            return ExerciseData (
                name = "Bench Press",
                sets = listOf(ExerciseSetData.defaultExerciseSet())
            )
        }
    }
}