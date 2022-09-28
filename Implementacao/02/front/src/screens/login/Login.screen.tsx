import './LoginScreen.css'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import { BasicInput } from '../../components/inputs/BasicInput.component'
import { VisibilityInput } from '../../components/inputs/VisibilityInput.component'
import { BasicForm } from '../../components/form/BasicForm.component'
import { BasicButton } from '../../components/buttons/BasicButton.component'
import { loginUser } from '../../services/login.service'

export const LoginScreen = () => {
	const [email, setEmail] = useState('')
	const [password, setPassword] = useState('')

	const getInputs = () => {
		const emailComponent = (
			<BasicInput label='Email' value={email} onChange={setEmail} isRequired inputType='email' />
		)
		const passWordComponent = (
			<>
				<VisibilityInput
					label='Senha'
					value={password}
					onChange={setPassword}
					isRequired
					inputType='password'
				/>
			</>
		)
		return [emailComponent, passWordComponent]
	}

	const onSubmitForm = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        window.location.href = '/automoveis'
		const { containsError, error } = await loginUser(email, password)
		if (containsError) {
			console.log(error)
			alert(error)
		} else {
			alert('Usuário logado com sucesso!')
			window.location.href = '/automoveis'
		}
	}

	return (
		<BasicForm
			title='Iniciar sessão'
			whereTheUserIsLabel=''
			customMessage='Não possui uma conta?'
			customMessageWhereToGo='/register'
			customMessageWhereToGoMessage='Criar uma conta'
			button={<BasicButton text='Iniciar Sessão' onClick={() => {}} />}
			inputs={getInputs()}
			onSubmit={onSubmitForm}
		/>
	)
}
