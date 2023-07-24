package com.kabe.quizapp.playscreen


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.kabe.quizapp.R

@Composable
fun getCategorySelectedValue(categoryName: String): String {

    val categoryItems = stringArrayResource(id = R.array.category)
    val categoryApiValueItems = stringArrayResource(id = R.array.category_api_value)

    val categoryMap = mapOf(
        categoryItems[0] to stringResource(R.string.label_any),
        categoryItems[1] to categoryApiValueItems[0],
        categoryItems[2] to categoryApiValueItems[1],
        categoryItems[3] to categoryApiValueItems[2],
        categoryItems[4] to categoryApiValueItems[3],
        categoryItems[5] to categoryApiValueItems[4],
        categoryItems[6] to categoryApiValueItems[5],
        categoryItems[7] to categoryApiValueItems[6],
        categoryItems[8] to categoryApiValueItems[7],
        categoryItems[9] to categoryApiValueItems[8]
        // Add more mappings here if needed
    )

    return categoryMap[categoryName] ?: ""
}

@Composable
fun getConvertedQuestionType(selectedQuestionType: String): String {
    val questionTypeItems = stringArrayResource(id = R.array.question_type)
    val convertedQuestionTypeItems = stringArrayResource(id = R.array.converted_question_type)

    val questionTypeMap = mapOf(
        questionTypeItems[0] to stringResource(R.string.label_any),
        questionTypeItems[1] to convertedQuestionTypeItems[0],
        questionTypeItems[2] to convertedQuestionTypeItems[1]
        // Add more mappings here if needed
    )

    return questionTypeMap[selectedQuestionType] ?: ""
}