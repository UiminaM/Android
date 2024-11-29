package hometask.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import hometask.myapplication.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        parentFragmentManager.popBackStack()

        binding?.launch?.setOnClickListener {
            showDialog()
        }

        binding?.info?.setOnClickListener {
            showSheet()
        }
    }

    private fun showDialog(){
        AlertDialog.Builder(requireContext())
            .setTitle("Age verification")
            .setMessage("Are you already 18 years old?")
            .setPositiveButton("OK") { dialog, _ ->
                (activity as? MainActivity)?.navigateToFragment(SecondFragment())
            }
            .setNegativeButton("NO") { dialog, _ ->
                (activity as? MainActivity)?.navigateToFragment(FirstFragment())
            }
            .setNeutralButton("Cancel")  { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSheet() {
        val bSheet = BottomSheetDialog(requireContext())
        with(bSheet) {
            setTitle("News!")
            setContentView(R.layout.bottomsheet)
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val TAG = "HomeFragment"
        const val ARG_KEY = "ARG_KEY"

        fun newInstance(arg: String) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_KEY, arg)
            }
        }
    }
}

