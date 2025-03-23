package org.eski.ui.views.selectors

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.composables.core.ScrollArea
import com.composables.core.Thumb
import com.composables.core.VerticalScrollbar
import com.composables.core.rememberScrollAreaState
import org.eski.ui.animation.AnimateView
import org.eski.ui.util.grid
import org.eski.ui.views.text.CenteredVerticalText


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun DropDownSelector(
  vmx: DropdownSelectorViewModel,
  modifier: Modifier = Modifier,
  expanded: MutableState<Boolean> = mutableStateOf(false),
  background: Color = Color.White,
  textColor: Color = Color.DarkGray,
  roundedCornerShape: RoundedCornerShape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
) {
  val selectedName by vmx.selectedName.collectAsState()
  val options by vmx.options.collectAsState()

  Column(modifier = modifier) {
    Card(
      onClick = { expanded.value = !expanded.value },
      shape = roundedCornerShape,
      backgroundColor = background,
      elevation = 16.dp,
    ) {
      Row(
        modifier = Modifier.padding(grid).defaultMinSize(minWidth = 120.dp)
      ) {
        Icon(
          imageVector = Icons.Filled.ArrowDropDown,
          contentDescription = null,
        )
        CenteredVerticalText (
          text = selectedName,
          color = textColor,
        )
      }
    }
    AnimateView(
      visible = expanded.value,
      enter = expandVertically(animationSpec = tween(300, 0), initialHeight = { fullHeight -> 0 }),
      exit = shrinkVertically(animationSpec = tween(300), targetHeight = { fullHeight -> 0 })
    ) {
      Card(
        backgroundColor = background,
        elevation = 16.dp,
        modifier = Modifier
          .fillMaxHeight()
          .padding(bottom = grid)
          .width(120.dp)
          .zIndex(501f)
      ) {
        val lazyListState = rememberLazyListState()
        val state = rememberScrollAreaState(lazyListState)

        ScrollArea(state = state) {
          LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
            options.forEachIndexed { index, option ->
              item {
                CenteredVerticalText(
                  text = option.name,
                  fontSize = 14.sp,
                  color = if (option.enabled) textColor else textColor.copy(alpha = .4f),
                  modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                      if (option.enabled) {
                        vmx.onSelected.invoke(index)
                        vmx.onSelectedOption.invoke(option)
                        expanded.value = false
                      }
                    }
                    .padding(grid)
                )
              }
            }
          }
          VerticalScrollbar(
            modifier = Modifier.align(Alignment.TopEnd)
              .fillMaxHeight()
              .width(4.dp)
          ) {
            Thumb(Modifier.background(Color.LightGray))
          }
        }
      }
    }
  }
}