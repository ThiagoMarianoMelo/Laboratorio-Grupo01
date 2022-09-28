import './LabelWithIcon.css'

export interface LabelWithIconProps {
	icon: string
	label: string
	isIconInLeft?: boolean
	customContainerClass?: string
	onClick?: () => void
}

export const LabelWithIcon = (props: LabelWithIconProps) => {
	const { icon, label, isIconInLeft = true, customContainerClass = '', onClick = () => {} } = props
	const mainContainerClass = `${customContainerClass} label-with-icon-container`

	return (
		<div onClick={onClick} className={mainContainerClass}>
			{isIconInLeft ? (
				<div className='label-with-icon-left'>
					<i className={icon} />
					<span>{label}</span>
				</div>
			) : (
				<div className='label-with-icon-right'>
					<span>{label}</span>
					<i className={icon} />
				</div>
			)}
		</div>
	)
}
