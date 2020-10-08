package com.bdd.myunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var edtWidht: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculateVolume: Button
    private lateinit var btnCalculateSurfaceArea: Button
    private lateinit var btnCalculateCircumference: Button
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_extension.text = ""

        mainViewModel = MainViewModel(CuboidModel())

        edtWidht = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        tvResult = findViewById(R.id.tv_result)
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume)
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area)
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
        btnCalculateVolume.setOnClickListener(this)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateCircumference.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val length = edtLength.text.toString().trim()
        val width = edtWidht.text.toString().trim()
        val height = edtHeight.text.toString().trim()

        when {
            length.isEmpty() -> edtLength.error = "Field ini tidak bole kosong"
            width.isEmpty() -> edt_length.error = "Field ini tidak bole kosong"
            height.isEmpty() -> edt_height.error = "Field ini tidak bole kosong"

            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when (v?.id){
                    R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun gone() {
        btnCalculateVolume.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnCalculateCircumference.visibility = View.GONE
        btnSave.visibility = View.VISIBLE
    }

    private fun visible() {
        btnCalculateVolume.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnCalculateCircumference.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
    }

}