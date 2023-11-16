package com.example.hoteltestapp.util.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import com.example.hoteltestapp.R
import com.example.hoteltestapp.databinding.ItemTouristTableBinding
import com.example.hoteltestapp.domain.events.BookEvents
import com.example.hoteltestapp.util.extension.changeVisible
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TouristTableAdapter(
    private val items: Map<Int,Boolean>,
    private val onEvent: (BookEvents) -> Unit
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
        binding.touristHeader.text = "${position.plus(1)} турист"
        changeVisibility(items[position] ?: false)

        binding.apply {
            nameEditText.doOnTextChanged { _, _, _, _ ->
                onEvent.invoke(BookEvents.OnNameChange(position,nameEditText.text.toString()))
            }
            surnameEditText.doOnTextChanged { _, _, _, _ ->
                onEvent.invoke(BookEvents.OnSurnameChange(position,surnameEditText.text.toString()))
            }
            dateEditText.doOnTextChanged { _, _, _, _ ->
                onEvent.invoke(BookEvents.OnDateChange(position,dateEditText.text.toString()))
            }
            citizenshipEditText.doOnTextChanged { _, _, _, _ ->
                onEvent.invoke(BookEvents.OnCitizenShipChange(position,citizenshipEditText.text.toString()))
            }
            passNumEditText.doOnTextChanged { _, _, _, _ ->
                onEvent.invoke(BookEvents.OnPassNumChange(position,passNumEditText.text.toString()))
            }
            passValidalityEditText.doOnTextChanged { _, _, _, _ ->
                onEvent.invoke(BookEvents.OnPassValidalityChange(position,passValidalityEditText.text.toString()))
            }
        }


        binding.expandButton.setOnClickListener {
            Log.i("debugmode",items.toString())
            onEvent.invoke(BookEvents.OnChangeVisibility(position))
        }
    }

    override fun getItemCount(): Int = items.size

    private fun changeVisibility(visibility: Boolean) {
        binding.apply {
            nameLayout.changeVisible(visibility)
            surnameLayout.changeVisible(visibility)
            dateLayout.changeVisible(visibility)
            citizenshipLayout.changeVisible(visibility)
            passNumLayout.changeVisible(visibility)
            passValidalityLayout.changeVisible(visibility)
            if(visibility) expandButton.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_arrow_up_blue))
            else expandButton.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_arrow_down_blue))
        }
    }
}