package com.example.rentmycar.ui.view.fragment


//class CreateReservationFragment : Fragment() {
//
//    private val epoxyController = ReservationEpoxyController(::onBtnBackClicked, ::onBtnPayClicked, ::dropdownFieldBinding)
//    private val safeArgs: CreateReservationFragmentArgs by navArgs()
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private var paymentMethods: Array<String> = emptyArray()
//    private val viewModel: ReservationViewModel by lazy {
//        ViewModelProvider(this)[ReservationViewModel::class.java]
//    }
//
//    private val viewmodel: ReservationViewModel by lazy {
//        ViewModelProvider(this)[ReservationViewModel::class.java]
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create__reservation_fragment, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val reservationNumber = safeArgs.reservationNumber
//
//        viewModel.reservationLiveData.observe(viewLifecycleOwner) { reservation ->
//            epoxyController.reservation = reservation
//            epoxyController.isDetailsView = safeArgs.isDetailsView
//
//            if (reservation == null) {
//                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.network_call_failed), Toast.LENGTH_LONG).show()
//                return@observe
//            }
//        }
//        viewModel.getReservation(reservationNumber)
//
//        observePayment()
//
//        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
//        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
//    }
//
//    private fun onBtnBackClicked() {
//        val directions = CreateReservationFragmentDirections.actionReservationCreateFragmentToCarListFragment()
//        findNavController().navigate(directions)
//    }
//
//    private fun onBtnPayClicked(reservationNumber: String) {
//        val paymentMethod = mapPaymentMethodToEnumValue(autoCompleteTextView.text.toString())
//
//        if (!paymentMethods.contains(paymentMethod)) {
//            autoCompleteTextView.error = HomeCustomerActivity.context.getString(R.string.payment_method_empty)
//        }
//        autoCompleteTextView.error = null
//
//        val paymentRequest = PostPaymentRequest(
//            reservation = ReservationNumberRequest(reservationNumber),
//            paymentMethod = paymentMethod
//        )
//        viewmodel.postPayment(paymentRequest)
//    }
//
//    private fun dropdownFieldBinding(autoCompleteTextView: AutoCompleteTextView) {
//        this.autoCompleteTextView = autoCompleteTextView
//        paymentMethods = resources.getStringArray(R.array.payment_methods)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.model_dropdown_list_item, paymentMethods)
//        autoCompleteTextView.setAdapter(arrayAdapter)
//    }
//
//    private fun observePayment() {
//        viewmodel.paymentLiveData.observe(viewLifecycleOwner) { payment ->
//            if (payment != null) {
//                val directions =
//                    CreateReservationFragmentDirections.actionReservationCreateFragmentToReservationPaymentFragment(paymentId = payment.id)
//                findNavController().navigate(directions)
//            }
//        }
//    }
//
//    private fun mapPaymentMethodToEnumValue(paymentMethod: String): String {
//        var paymentEnumType: String = ""
//
//        // Make sure correct ENUM type is sent to API.
//        when(true) {
//            paymentMethod.contains("ideal", ignoreCase = true) -> {
//                paymentEnumType = "IDEAL"
//            }
//            paymentMethod.contains("visa", ignoreCase = true) -> {
//                paymentEnumType = "VISA_CARD"
//            }
//            paymentMethod.contains("mastercard", ignoreCase = true) -> {
//                paymentEnumType = "MASTER_CARD"
//            }
//            paymentMethod.contains("paypal", ignoreCase = true) -> {
//                paymentEnumType = "PAYPAL"
//            }
//            else -> {
//                Toast.makeText(requireActivity(), HomeCustomerActivity.context.getString(R.string.invalid_payment_method), Toast.LENGTH_LONG).show()
//            }
//        }
//        return paymentEnumType
//    }
//
//
//}