package com.example.potatoservice.ui.home

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.potatoservice.databinding.SpinnerItemBinding
import org.w3c.dom.Text

class SpinnerAdapter(
	private val items: List<List<String>>
): RecyclerView.Adapter<SpinnerAdapter.SpinnerViewHolder>() {
	private lateinit var binding: SpinnerItemBinding
	inner class SpinnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val spinner = binding.spinner
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinnerViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		binding = SpinnerItemBinding.inflate(inflater, parent, false)
		return SpinnerViewHolder(binding.root)
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {
		val spinnerItems = items[position]

		val adapter = SpinnerHintAdapter(
			holder.spinner.context, R.layout.simple_spinner_dropdown_item, spinnerItems)
		holder.spinner.adapter = adapter

		holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				val selectedItem = parent?.getItemAtPosition(position)?.toString()
				Log.d("testt", "selectedItem: $selectedItem")
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
	}
}