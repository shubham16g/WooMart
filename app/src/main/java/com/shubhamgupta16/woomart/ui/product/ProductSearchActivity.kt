package com.shubhamgupta16.woomart.ui.product

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import android.widget.Toast
import androidx.activity.viewModels
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.common.BaseActivity
import com.shubhamgupta16.woomart.ui.state.ProgressDialogFragment
import com.shubhamgupta16.woomart.viewmodels.ProductViewModel

import kotlinx.android.synthetic.main.activity_product_search.*
import kotlinx.android.synthetic.main.content_shop.*
import com.shubhamgupta16.woomart.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import me.gilo.woodroid.callback.Status
import me.gilo.woodroid.models.Product
import org.json.JSONObject
import java.util.ArrayList



@AndroidEntryPoint
class ProductSearchActivity : BaseActivity() {

    lateinit var adapter: ProductAdapter
    lateinit var products: ArrayList<Product>
    lateinit var toggle: ActionBarDrawerToggle

    val viewModel: ProductViewModel by viewModels()
    val TAG = this::getLocalClassName

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search)
        setSupportActionBar(toolbar)


        title = "Search"

        val layoutManager =
            GridLayoutManager(baseContext, 2)
        rvShop.layoutManager = layoutManager
        rvShop.isNestedScrollingEnabled = false

        products = ArrayList()

        adapter = ProductAdapter(products)
        rvShop.adapter = adapter

        handleIntent(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            query?.let { search(it) }
        }
    }

    private fun search(query : String) {
        viewModel.search(query).observe(this, androidx.lifecycle.Observer { response ->
            when (response!!.status()) {
                Status.LOADING -> {
                    showLoading("Performing search", "This will only take a short while")
                }

                Status.SUCCESS -> {
                    stopShowingLoading()

                    val productsResponse = response.data()
                    for (product in productsResponse) {
                        products.add(product)
                    }

                    adapter.notifyDataSetChanged()

                }

                Status.ERROR -> {
                    stopShowingLoading()

                    var message: String
                    var loginError = JSONObject(response.error().message)

                    if (loginError.has("status_message")) {
                        message = loginError.getString("status_message")
                    } else {
                        message = response.error().message.toString()
                    }

                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                }

                Status.EMPTY -> {
                    stopShowingLoading()
                }
            }

        })



    }

    private lateinit var progressDialog: ProgressDialogFragment

    fun showLoading(title: String, message: String) {
        val manager = supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun showLoading() {
        showLoading("This will only take a sec", "Loading")
    }

    fun stopShowingLoading() {
        progressDialog.dismiss()
    }

}
