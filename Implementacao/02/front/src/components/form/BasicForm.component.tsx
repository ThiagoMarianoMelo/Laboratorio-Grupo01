import { Link } from 'react-router-dom'
import './BasicForm.css'

export interface BasicFormProps {
	whereTheUserIsLabel: string
	title: string
	customMessage: string
	customMessageWhereToGo: string
	customMessageWhereToGoMessage: string
	inputs: JSX.Element[]
	button: JSX.Element
	onSubmit: (event: React.FormEvent<HTMLFormElement>) => void
}

export const BasicForm = (props: BasicFormProps) => {
	const {
		whereTheUserIsLabel,
		title,
		customMessage,
		customMessageWhereToGo,
		customMessageWhereToGoMessage,
		inputs,
		button,
		onSubmit
	} = props

	const formatInput = () => {
		return inputs.map(input => <div className='form_input_container'>{input}</div>)
	}

	return (
		<div className='form_main_container'>
			<div className='form_description'>
				<span>{whereTheUserIsLabel}</span>
				<h1>{title}</h1>
			</div>
			<form className='form_container' onSubmit={onSubmit}>
				{formatInput()}
				<div className='form_button_container'>{button}</div>
			</form>
			<div className='form_custom_message'>
				{customMessage} <Link to={customMessageWhereToGo}>{customMessageWhereToGoMessage}</Link>
			</div>
		</div>
	)
}
