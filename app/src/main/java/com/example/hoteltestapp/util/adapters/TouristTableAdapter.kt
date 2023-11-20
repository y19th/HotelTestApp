package com.example.hoteltestapp.util.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.hoteltestapp.R
import com.example.hoteltestapp.databinding.ItemTouristTableBinding
import com.example.hoteltestapp.domain.events.BookEvents
import com.example.hoteltestapp.domain.events.InputType
import com.example.hoteltestapp.domain.model.Tourist
import com.example.hoteltestapp.util.extension.changeVisible
import com.example.hoteltestapp.util.extension.error
import com.example.hoteltestapp.util.extension.onLoseFocus
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TouristTableAdapter(
    private val items: Map<Int,Boolean>,
    private val onEvent: (BookEvents) -> Unit,
    private val previousData: List<Tourist>,
    private val viewLifecycleOwner: LifecycleOwner
) : BaseAdapter<ItemTouristTableBinding>(), KoinComponent {

    private val context: Context by inject()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemTouristTableBinding> {
        _binding = ItemTouristTableBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemTouristTableBinding>, position: Int) {
        changeVisibility(items[position] ?: false)

        binding.apply {
            touristHeader.text = "${position.plus(1)} турист"

            nameEditText.also {
                it.doOnTextChanged { _, _, _, _ ->
                    nameLayout.error(false)
                    onEvent.invoke(BookEvents.OnNameChange(position,nameEditText.text.toString()))
                }
                it.setText(previousData[position].name)
                nameLayout.error(previousData[position].isNameError)
            }.onLoseFocus(type = InputType.Text, onLoseFocus = onEvent, parent = nameLayout)
            surnameEditText.also {
                it.doOnTextChanged { _, _, _, _ ->
                    surnameLayout.error(false)
                    onEvent.invoke(BookEvents.OnSurnameChange(position,surnameEditText.text.toString()))
                }
                it.setText(previousData[position].surname)
                surnameLayout.error(previousData[position].isSurnameError)
            }.onLoseFocus(type = InputType.Text, onLoseFocus = onEvent, parent = surnameLayout)
            dateEditText.also {
                it.doOnTextChanged { _, start, _, end ->
                    when (dateEditText.text?.length) {
                        3, 6 -> {
                            if (end != 0 && start != 0) dateEditText.editableText.insert(start, ".")
                        }
                    }
                    dateLayout.error(false)
                    onEvent.invoke(BookEvents.OnDateChange(position, dateEditText.text.toString()))
                }
                it.setText(previousData[position].date)
                dateLayout.error(previousData[position].isDateError)
            }.onLoseFocus(type = InputType.Date, onLoseFocus = onEvent, parent = dateLayout)
            citizenshipEditText.also {
                it.doOnTextChanged { _, _, _, _ ->
                    citizenshipLayout.error(false)
                    onEvent.invoke(BookEvents.OnCitizenShipChange(position,citizenshipEditText.text.toString()))
                }
                it.setText(previousData[position].citizenShip)
                citizenshipLayout.error(previousData[position].isCitizenError)
            }.onLoseFocus(type = InputType.Text, onLoseFocus = onEvent, parent = citizenshipLayout)
            passNumEditText.also {
                it.doOnTextChanged { _, _, _, _ ->
                    passNumLayout.error(false)
                    onEvent.invoke(BookEvents.OnPassNumChange(position,passNumEditText.text.toString()))
                }
                it.setText(previousData[position].passNum)
                passNumLayout.error(previousData[position].isPassNumError)
            }.onLoseFocus(type = InputType.Pass, onLoseFocus = onEvent, parent = passNumLayout)
            passValidalityEditText.also {
                it.doOnTextChanged { _, start, _, end ->
                    when (passValidalityEditText.text?.length) {
                        3, 6 -> {
                            if (end != 0 && start != 0) passValidalityEditText.editableText.insert(start, ".")
                        }
                    }
                    passValidalityLayout.error(false)
                    onEvent.invoke(BookEvents.OnPassValidalityChange(position,passValidalityEditText.text.toString()))
                }
                it.setText(previousData[position].passValidality)
                passValidalityLayout.error(previousData[position].isPassValidalityError)
            }.onLoseFocus(type = InputType.Date, onLoseFocus = onEvent, parent = passValidalityLayout)

            expandButton.setOnClickListener {
                Log.i("debugmode",items.toString())
                onEvent.invoke(BookEvents.OnChangeVisibility(position))
            }
        }

    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return previousData[position].position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return previousData[position].position
    }


    private fun changeVisibility(visibility: Boolean) {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.apply {
                nameLayout.changeVisible(visibility)
                surnameLayout.changeVisible(visibility)
                dateLayout.changeVisible(visibility)
                citizenshipLayout.changeVisible(visibility)
                passNumLayout.changeVisible(visibility)
                passValidalityLayout.changeVisible(visibility)
                if (visibility) expandButton.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_arrow_up_blue
                    )
                )
                else expandButton.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_arrow_down_blue
                    )
                )
            }
        }
    }
}