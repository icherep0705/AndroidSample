package com.example.androidsampleapp.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsampleapp.R
import com.example.androidsampleapp.databinding.CartListItemBinding
import com.example.androidsampleapp.service.CardObject
import com.example.androidsampleapp.service.Info

class PageAdapter: RecyclerView.Adapter<PageViewHolder>() {

    var cardList: List<CardObject> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var mBinding: CartListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cart_list_item, parent, false)
        return PageViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount() = cardList.size
}

class PageViewHolder(private val binding: CartListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(cardObj: CardObject) {
        binding.apply {
            if (cardObj.cardType == CARD_TYPE_TEXT) {
                cardObj.card?.let {
                    titleLabel.text = it.value
                    titleLabel.visibility = View.VISIBLE
                    it.attributes?.let {attr ->
                        titleLabel.setTextColor(Color.parseColor(attr.textColor))
                        titleLabel.textSize = attr.font?.size?.toFloat() ?: 30f
                    }
                }

            } else {

                cardObj.card?.apply {
                    title?.let { setValues(it, titleLabel) }
                    description?.let { setValues(it, descriptionLabel) }
                    image?.let {
                        imageBg.apply {
                            requestLayout()
                            it.size?.height?.let {  layoutParams.height = it }
                            it.size?.width?.let {  layoutParams.width = it }
                            scaleType = ImageView.ScaleType.FIT_XY
                        }

                        Glide
                            .with(imageBg.context)
                            .load(it.url)
                            .into(imageBg)
                    }

                }
            }
        }
    }

    private fun setValues(info: Info, v: TextView){
        v.text = info.value
        v.visibility = View.VISIBLE
        info.attributes?.let {attr ->
            v.setTextColor(Color.parseColor(attr.textColor))
            v.textSize = attr.font?.size?.toFloat() ?: 30f
        }
    }

    companion object {
        private const val CARD_TYPE_TEXT = "text"
    }
}
