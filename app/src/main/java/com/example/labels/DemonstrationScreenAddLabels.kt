package com.example.labels

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun DemonstrationScreenAddLabels() {
    val labelsExamples = remember {
        mutableStateListOf(
            "Отчет",
            "Продажи",
            "Важно",
            "Важно и срочно",
            "Маркетинг",
            "Идея",
            "Для бухгалтерии",
            "Курьер",
            "Критическая ошибка",
            "В2В"
        )
    }

    val labels = remember {
        mutableStateListOf(
            LabelInfo(text = "салон красоты"),
            LabelInfo(text = "очень критическая ошибка", isDeletable = false),
            LabelInfo(text = "ID менеджера сопровождения компании: 14567924752475743989"),
            LabelInfo(text = "ID ученика: 4845683", isDeletable = false),
            LabelInfo(text = "ID персонального менеджера: 143454245683"),
            LabelInfo(text = "b2b"),
            LabelInfo(text = "b2b"),
        )
    }

    var textField by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(
            all = 20.dp
        )
    ) {
        RowFlex(
            horizontalSpace = 10.dp,
            verticalSpace = 10.dp
        ) {
            for (label in labels) {
                LabelDeletable(
                    text = label.text,
                    isDeletable = label.isDeletable,
                    onClickDelete = {
                        labels.remove(label)
                    }
                )
            }
        }

        val focusManager = LocalFocusManager.current
        TextField(
            value = textField,
            onValueChange = {
                textField = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(
                    1f, 1f, 1f, 0f
                ),
                focusedIndicatorColor = Color(
                    red = 218,
                    green = 218,
                    blue = 218
                ),
                unfocusedIndicatorColor = Color(
                    red = 218,
                    green = 218,
                    blue = 218
                ),
                errorIndicatorColor = Color.Red
            ),
            textStyle = ServiceLocator.textStyleScreenAddLabels,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(onSend = {
                labels.add(LabelInfo(text = textField))
                textField = ""
                focusManager.clearFocus()
            })


        )

        LazyColumn(
            contentPadding = PaddingValues(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(labelsExamples) { label ->
                Label(text = label,
                    onClick = {
                        labels.add(LabelInfo(text = label))
                    })
            }
        }
    }
}