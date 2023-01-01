package com.example.khabeer_task.ui.main.employee

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khabeer_task.data.responeses.employee.Allowences
import com.example.khabeer_task.databinding.FragmentEmployeeBinding
import com.example.khabeer_task.util.BaseApplication
import com.raminabbasiiii.movies.util.Status
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.android.PieChart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.format.DateTimeFormatter


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EmployeeFragment : Fragment() {


  //  https://github.com/alcarazolabs/FastTableLayout

    private lateinit var viewModel: EmployeeViewModel
    lateinit var binding: FragmentEmployeeBinding
    private var mTableLayout: TableLayout? = null
//    var mProgressBar: ProgressDialog? = null
     lateinit var adapter : CustomAdapter
     lateinit var pieChart:PieChart
     private val args: EmployeeFragmentArgs by navArgs()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        binding = FragmentEmployeeBinding.inflate(layoutInflater)
//        mProgressBar =  ProgressDialog(BaseApplication.applicationContext());


        binding.employeeViewModel = viewModel
        viewModel.setToken(args.token)
//        get_data()
        pieChart=binding.pieChart


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            get_data()
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    fun get_data(){
        viewModel.movieDetails.observe(viewLifecycleOwner) { result ->
            when(result.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    result.data?.let { data ->
                        binding.txtName.text= data.Payroll!!.Employee[0].EMPDATAEN
                        binding.textType.text= data.Payroll!!.Employee[0].JOBNAMEEN
                        // totla Allowences
                        var total_Allowences=0.0;
                        var total_Deduction=0.0;
                        var total_salary=0.0;
                        var total=0.0;



                        val  mList: ArrayList<Allowences> = arrayListOf()
                        data.Payroll?.Allowences?.let { mList.addAll(it) }

                        data.Payroll?.Allowences?.forEach {
                            total_Allowences=total_Allowences+ it.SALVALUE!!
                        }

                        data.Payroll?.Deduction?.forEach {
                            total_Deduction=total_Deduction+ it.SALVALUE!!
                            mList.add(Allowences(it.EMPID,it.SALCOMPCODE,it.COMPDESCAR,it.COMPDESCEN,it.SALVALUE,it.SALCOMPTYPE))
                        }

                        total_salary=total_Allowences-total_Deduction
                        total=total_Allowences+total_Deduction

                        binding.total.text= (Math.round(total_salary * 100.0) / 100.00).toString()
                        binding.totlaSalary.text= (Math.round(total_salary * 100.0) / 100.00).toString()


                        // data
                        val formatter = DateTimeFormatter.ofPattern("MM/yyyy")
                        val date = formatter.parse(result.data.Payroll!!.Date)
                        val desiredFormat = DateTimeFormatter.ofPattern("MMM").format(date)
                        binding.textViewDate.text=desiredFormat


                        adapter=CustomAdapter(mList)
                        binding.recylerviewEmp.setHasFixedSize(true);
                       binding.recylerviewEmp.adapter=adapter
                        binding.recylerviewEmp.setAdapter(adapter);
                        binding.recylerviewEmp.layoutManager= LinearLayoutManager(getActivity())
                        adapter.notifyDataSetChanged()
                         val   values_allownces=total_Allowences/total
                        val   values_deduction=total_Deduction/total


                        pieChart.slices = listOf(
                            PieChart.Slice(values_allownces.toFloat(), Color.GRAY),
                            PieChart.Slice(values_deduction.toFloat(), Color.YELLOW),
                        )
                        binding.totlaAllowences.text="${total_Allowences} Entitlements"
                        binding.totlaDeduction.text="${total_Deduction} Deductions"

                    }
//                    val fastTable = FastTableLayout(BaseApplication.applicationContext(),
//                        binding.myTableLayout,
//                        headers,
//                        data_table)
//                    fastTable.build()
                }
                Status.ERROR -> {

                }
            }
        }

    }

}