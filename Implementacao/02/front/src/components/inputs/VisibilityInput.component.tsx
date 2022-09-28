import { useState } from 'react'
import { BasicInput, BasicInputProps } from './BasicInput.component'
import './VisibilityInput.css'

export interface VisibilityInputProps extends BasicInputProps {}

export const VisibilityInput = (props: VisibilityInputProps) => {
	const {
		label,
		value,
		onChange,
		customInputStyle,
		customLabelStyle,
		placeholder = '',
		inputType = 'text',
		isRequired = false
	} = props

	const [isVisible, setVisibility] = useState(true)
	const getVisibilityIconClass = () => {
		const defaultClass = 'fa visibility_icon_positon'

		return isVisible ? `${defaultClass} fa-eye` : `${defaultClass} fa-low-vision`
	}

	return (
		<div className='visibility_input_container'>
			<BasicInput
				label={label}
				value={value}
				onChange={onChange}
				isRequired={isRequired}
				inputType={isVisible ? inputType : 'text'}
				placeholder={placeholder}
				customInputStyle={customInputStyle}
				customLabelStyle={customLabelStyle}
			/>
			<i className={getVisibilityIconClass()} onClick={_ => setVisibility(!isVisible)} />
		</div>
	)
}
