import './BasicButton.css'

export interface BasicButtonProps {
	text: string
	onClick: React.MouseEventHandler<HTMLButtonElement>
}

export const BasicButton = (props: BasicButtonProps) => {
	const { text, onClick } = props

	return (
		<button className='basic_button_container' onClick={onClick}>
			{text}
		</button>
	)
}
