import { HTMLInputTypeAttribute } from 'react'
import './BasicInput.css'

export interface BasicInputProps {
	label: string
	value: string
	onChange: (value: string) => void
	placeholder?: string
	isRequired?: boolean
	inputType?: HTMLInputTypeAttribute
	customLabelStyle?: string
	customInputStyle?: string
}

export const BasicInput = (props: BasicInputProps) => {
	const {
		label,
		value,
		onChange,
		placeholder = '',
		inputType = 'text',
		isRequired = false,
		customLabelStyle = '',
		customInputStyle = ''
	} = props

	const formattedLabel = isRequired ? `* ${label}` : label
	return (
		<div className={`basic_input_container ${customLabelStyle}`}>
			<label htmlFor=''>{formattedLabel}</label>
			<input
				className={`basic_input_input ${customInputStyle}`}
				value={value}
				required={isRequired}
				type={inputType}
				placeholder={placeholder}
				onChange={ev => onChange(ev.target.value)}
			/>
		</div>
	)
}
