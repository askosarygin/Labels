package com.example.labels

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DemonstrationScreenLabels() {
    val labels = remember {
        mutableStateListOf(
            "салон красоты",
            "очень критическая ошибка",
            "ID менеджера сопровождения компании: 14567924752475743989",
            "ID ученика: 4845683",
            "ID персонального менеджера: 143454245683",
            "b2b",
            "b2b"
        )
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var amountOfHiddenElements by remember {
        mutableStateOf(0)
    }

    var maxLinesWhenHidden by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LabelDescription(
                textLabelDescription = "Метки",
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }


        RowFlex(
            modifier = Modifier.animateContentSize(),
            verticalSpace = 10.dp,
            horizontalSpace = 10.dp,
            maxLinesWhenHidden = maxLinesWhenHidden,
            amountVisibleElementsWhenHidden = { amount ->
                amountOfHiddenElements = labels.size - amount
            },
            isExpanded = isExpanded,
            expandButton = {
                ExpandTextButton(text = "и еще $amountOfHiddenElements") {
                    isExpanded = true
                }
            },
            shrinkButton = {
                ShrinkTextButton(text = "Свернуть") {
                    isExpanded = false
                }
            }
        ) {
            for (label in labels) {
                Label(text = label)
            }
        }

        //buttons for test
        /*
            Сценарии:
            1. 7 меток
            2. 27 меток
            3. 1 нормальная метка
            4. 1 огромная метка
            5. 1 пустая метка
            6. 15 пустых меток
            7. удалить все метки
            8. 1 короткая и 1 длинная метка
            9. 1 длинная и 1 короткая метка
            10. сделать количество рядов = 1
            11. сделать количество рядов = 2
            12. сделать количество рядов = 3
            */
        RowFlex(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        "салон красоты",
                        "очень критическая ошибка",
                        "ID менеджера сопровождения компании: 14567924752475743989",
                        "ID ученика: 4845683",
                        "ID персонального менеджера: 143454245683",
                        "b2b",
                        "b2b"
                    )
                )
            }) {
                Text(text = "7 меток")
            }

            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        "первая метка",
                        "вторая метка",
                        "третья метка",
                        "четвертая метка",
                        "пятая метка",
                        "шестая метка",
                        "седьмая метка",
                        "8 метка",
                        "9 метка",
                        "10 метка",
                        "11 метка",
                        "12 метка",
                        "13 метка",
                        "14 метка",
                        "15 метка",
                        "16 метка",
                        "17 метка",
                        "18 метка",
                        "19 метка",
                        "20 метка",
                        "21 метка",
                        "22 метка",
                        "23 метка",
                        "24 метка"
                    )
                )
            }) {
                Text(text = "24 метки")
            }

            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        "одна метка"
                    )
                )
            }) {
                Text(text = "1 метка")
            }

            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        "одна огроооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооомная метка"
                    )
                )
            }) {
                Text(text = "1 огромная метка")
            }

            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        ""
                    )
                )
            }) {
                Text(text = "1 пустая метка")
            }

            Button(onClick = {
                labels.clear()
                for (i in 0..14)
                    labels.add("")
            }) {
                Text(text = "15 пустых меток")
            }

            Button(onClick = {
                labels.clear()
            }) {
                Text(text = "удалить все метки")
            }

            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        "короткая",
                        "длиииииииинннннннннннааааааааааяяяяяяя"
                    )
                )
            }) {
                Text(text = "1 короткая и 1 длинная")
            }

            Button(onClick = {
                labels.clear()
                labels.addAll(
                    listOf(
                        "длиииииииинннннннннннааааааааааяяяяяяя",
                        "короткая"
                    )
                )
            }) {
                Text(text = "1 длинная и 1 короткая")
            }

            Button(onClick = {
                maxLinesWhenHidden = 1
            }) {
                Text(text = "сделать количество рядов 1")
            }

            Button(onClick = {
                maxLinesWhenHidden = 2
            }) {
                Text(text = "сделать количество рядов 2")
            }

            Button(onClick = {
                maxLinesWhenHidden = 3
            }) {
                Text(text = "сделать количество рядов 3")
            }
        }
    }
}