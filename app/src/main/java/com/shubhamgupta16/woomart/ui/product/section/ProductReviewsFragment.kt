package com.shubhamgupta16.woomart.ui.product.section

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.section_product_reviews.*
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.common.BaseActivity
import com.shubhamgupta16.woomart.events.ReviewEvent
import com.shubhamgupta16.woomart.viewmodels.ReviewViewModel
import com.shubhamgupta16.woomart.adapter.ProductReviewAdapter
import me.gilo.woodroid.callback.Status
import com.shubhamgupta16.woomart.ui.product.ProductActivity
import me.gilo.woodroid.models.ProductReview
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class ProductReviewsFragment : Fragment() {


    lateinit var viewModel: ReviewViewModel
    val TAG = "ProductReviewsFragment"
    var productId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.section_product_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as BaseActivity).getViewModel(
            ReviewViewModel::class.java)


        productId = (activity as ProductActivity).intent.getIntExtra("productId", 0)
        reviews(productId)

        tvAddAReview.setOnClickListener{addAReviewDialog()}
    }

    private fun reviews(productId : Int) {

        val layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvReviews.layoutManager = layoutManager
        rvReviews.isNestedScrollingEnabled = false

        var reviews = ArrayList<ProductReview>()

        var productReviewAdapter = ProductReviewAdapter(reviews)
        rvReviews.adapter = productReviewAdapter


        viewModel.reviews(productId).observe(viewLifecycleOwner, androidx.lifecycle.Observer { response ->
            when (response!!.status()) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    reviews.clear()

                    val reviewsResponse = response.data()
                    for (review in reviewsResponse) {
                        reviews.add(review)
                    }

                    productReviewAdapter.notifyDataSetChanged()

                }

                Status.ERROR -> {
                    Log.d("Error", response.error().message.toString())
                }

                Status.EMPTY -> {

                }
            }

        })

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductReviewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    lateinit var addAReviewFragment: AddAReviewDialogFragment

    private fun addAReviewDialog() {
        val manager = childFragmentManager
        addAReviewFragment =
            AddAReviewDialogFragment.newInstance(productId)
        addAReviewFragment.isCancelable = true
        addAReviewFragment.show(manager, "add Review")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ReviewEvent) {
        save(event.review)
    }

    private fun save(review: ProductReview) {
        viewModel.create(review).observe(this, androidx.lifecycle.Observer { response ->
            when (response!!.status()) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    val reviewsResponse = response.data()
                   reviews(productId)

                }

                Status.ERROR -> {
                    Log.d("Error", response.error().message.toString())
                }

                Status.EMPTY -> {

                }
            }

        })


    }

}
