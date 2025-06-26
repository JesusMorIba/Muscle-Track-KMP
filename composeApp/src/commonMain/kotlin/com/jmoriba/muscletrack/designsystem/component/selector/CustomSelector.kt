package com.jmoriba.muscletrack.designsystem.component.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun <T> CustomSelector(
    selectedItem: T?,
    items: List<T?>,
    onItemSelected: (T?) -> Unit,
    placeholder: String = "Select an option",
    leadingIcon: DrawableResource,
    itemDisplayText: (T?) -> String = { it?.toString() ?: "All" },
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selectedItem?.let { itemDisplayText(it) } ?: "",
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF9CA3AF),
                    fontSize = 16.sp
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                    tint = Color(0xFF6B7280),
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFF6B7280),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF6366F1),
                unfocusedBorderColor = Color(0xFFE5E7EB),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color(0xFF1F2937),
                unfocusedTextColor = Color(0xFF1F2937)
            ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
                .border(
                    1.dp,
                    Color(0xFFE5E7EB),
                    RoundedCornerShape(12.dp)
                )
                .padding(vertical = 8.dp),
            offset = DpOffset(0.dp, 4.dp)
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = itemDisplayText(item),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (item == selectedItem) Color(0xFF6366F1) else Color(0xFF1F2937)
                            )
                            if (item == selectedItem) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color(0xFF6366F1),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .background(
                            if (item == selectedItem) Color(0xFFF3F4F6) else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                )

                if (index < items.size - 1) {
                    HorizontalDivider(
                        color = Color(0xFFF3F4F6),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
    }
}