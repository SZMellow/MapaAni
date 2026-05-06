package com.example.mapaani

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoardingAdapter(
    private val boardingItems: List<BoardingItem>,
    private val onNextClicked: (Int) -> Unit,
    private val onSkipClicked: () -> Unit
) : RecyclerView.Adapter<BoardingAdapter.BoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingViewHolder {
        return BoardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_boarding,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardingViewHolder, position: Int) {
        holder.bind(boardingItems[position], position)
    }

    override fun getItemCount(): Int = boardingItems.size

    inner class BoardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgBackground = view.findViewById<ImageView>(R.id.imgBackground)
        private val imgIcon = view.findViewById<ImageView>(R.id.imgIcon)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        private val btnNext = view.findViewById<Button>(R.id.btnNext)
        private val tvSkip = view.findViewById<TextView>(R.id.tvSkip)
        
        private val indicator1 = view.findViewById<View>(R.id.indicator1)
        private val indicator2 = view.findViewById<View>(R.id.indicator2)
        private val indicator3 = view.findViewById<View>(R.id.indicator3)

        fun bind(boardingItem: BoardingItem, position: Int) {
            tvTitle.text = boardingItem.title
            tvDescription.text = boardingItem.description
            imgBackground.setImageResource(boardingItem.image)
            imgIcon.setImageResource(boardingItem.icon)
            
            // Handle Next button text
            if (position == boardingItems.size - 1) {
                btnNext.text = itemView.context.getString(R.string.boarding_get_started)
            } else {
                btnNext.text = itemView.context.getString(R.string.boarding_next)
            }
            
            // Handle Indicators
            val activeColor = itemView.context.getColor(R.color.Green1)
            val inactiveColor = itemView.context.getColor(R.color.JadeWhite)
            
            indicator1.setBackgroundColor(if (position == 0) activeColor else inactiveColor)
            indicator2.setBackgroundColor(if (position == 1) activeColor else inactiveColor)
            indicator3.setBackgroundColor(if (position == 2) activeColor else inactiveColor)

            btnNext.setOnClickListener {
                onNextClicked(position)
            }
            
            tvSkip.setOnClickListener {
                onSkipClicked()
            }
        }
    }
}
