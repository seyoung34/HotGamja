package com.example.potatoservice.ui.share

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.potatoservice.databinding.SpinnerItemBinding

class SpinnerAdapter(
	private val items: List<List<String>>,
	private val callback: AdapterCallback
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
		val spinnerPosition = position
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
				callback.onSpinnerSelected(spinnerPosition, position)
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
	}
}