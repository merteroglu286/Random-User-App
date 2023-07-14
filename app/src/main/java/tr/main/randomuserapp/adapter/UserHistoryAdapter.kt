package tr.main.randomuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tr.main.randomuserapp.databinding.ItemsRowBinding
import tr.main.randomuserapp.model.UserEntity

class UserHistoryAdapter (private val items: ArrayList<UserEntity>,
    private val deleteListener: (id: Int) -> Unit
    ) :
    RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemsRowBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
            val item = items[position]

            holder.tvName.text = item.name
            Glide.with(context).load(item.imageUrl).into(holder.image)

            holder.ivDelete.setOnClickListener {
                deleteListener(item.id)
            }

        }

        override fun getItemCount(): Int {
            return items.size
        }


        class ViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
            val tvName = binding.name
            val image = binding.imageview
            val ivDelete = binding.ivDelete
        }
    }