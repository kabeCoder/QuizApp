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
        categoryItems[9] to categoryApiValueItems[8],
        categoryItems[10] to categoryApiValueItems[9],
        categoryItems[11] to categoryApiValueItems[10],
        categoryItems[12] to categoryApiValueItems[11],
        categoryItems[13] to categoryApiValueItems[12],
        categoryItems[14] to categoryApiValueItems[13],
        categoryItems[15] to categoryApiValueItems[14],
        categoryItems[16] to categoryApiValueItems[15],
        categoryItems[17] to categoryApiValueItems[16],
        categoryItems[18] to categoryApiValueItems[17],
        categoryItems[19] to categoryApiValueItems[18],
        categoryItems[20] to categoryApiValueItems[19],
        categoryItems[21] to categoryApiValueItems[20],
        categoryItems[22] to categoryApiValueItems[21],
        categoryItems[23] to categoryApiValueItems[22],
        categoryItems[24] to categoryApiValueItems[23]
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