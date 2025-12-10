@extends('layouts.app')

@section('title', 'Admin Login')

@section('styles')
<style>
    .login-container {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 20px;
        position: relative;
        z-index: 1;
    }

    .login-card {
        background: rgba(255, 255, 255, 0.95);
        backdrop-filter: blur(20px);
        border-radius: var(--border-radius);
        padding: 50px;
        max-width: 480px;
        width: 100%;
        box-shadow: var(--shadow-lg);
        border: 1px solid rgba(255, 255, 255, 0.2);
        animation: fadeIn 0.6s ease-out;
    }

    .login-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .login-icon {
        width: 80px;
        height: 80px;
        background: linear-gradient(135deg, var(--primary), var(--secondary));
        border-radius: 20px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0 auto 20px;
        box-shadow: var(--shadow);
    }

    .login-icon i {
        font-size: 36px;
        color: white;
    }

    .login-title {
        font-size: 32px;
        font-weight: 800;
        background: linear-gradient(135deg, var(--primary), var(--secondary));
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
        margin-bottom: 10px;
    }

    .login-subtitle {
        color: var(--gray);
        font-size: 15px;
        font-weight: 500;
    }

    .form-group {
        margin-bottom: 24px;
    }

    .form-label {
        display: block;
        margin-bottom: 8px;
        font-weight: 600;
        color: var(--dark);
        font-size: 14px;
    }

    .form-label .required {
        color: var(--danger);
    }

    .input-group {
        position: relative;
    }

    .input-icon {
        position: absolute;
        left: 18px;
        top: 50%;
        transform: translateY(-50%);
        color: var(--gray);
        font-size: 18px;
    }

    .form-input {
        width: 100%;
        padding: 14px 18px 14px 50px;
        border: 2px solid var(--gray-light);
        border-radius: var(--border-radius-sm);
        font-size: 15px;
        font-family: inherit;
        transition: var(--transition);
        background: white;
    }

    .form-input:focus {
        outline: none;
        border-color: var(--primary);
        box-shadow: 0 0 0 4px rgba(99, 102, 241, 0.1);
    }

    .form-input.is-invalid {
        border-color: var(--danger);
    }

    .form-input.is-invalid:focus {
        box-shadow: 0 0 0 4px rgba(239, 68, 68, 0.1);
    }

    .invalid-feedback {
        display: block;
        margin-top: 8px;
        color: var(--danger);
        font-size: 13px;
        font-weight: 500;
    }

    .form-check {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-bottom: 24px;
    }

    .form-check-input {
        width: 18px;
        height: 18px;
        cursor: pointer;
        accent-color: var(--primary);
    }

    .form-check-label {
        font-size: 14px;
        color: var(--gray);
        cursor: pointer;
        user-select: none;
    }

    .btn {
        width: 100%;
        padding: 16px 28px;
        border: none;
        border-radius: var(--border-radius-sm);
        font-size: 16px;
        font-weight: 600;
        cursor: pointer;
        transition: var(--transition);
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 10px;
        font-family: inherit;
    }

    .btn-primary {
        background: linear-gradient(135deg, var(--primary), var(--secondary));
        color: white;
        box-shadow: 0 4px 15px rgba(99, 102, 241, 0.4);
    }

    .btn-primary:hover {
        transform: translateY(-2px);
        box-shadow: 0 6px 20px rgba(99, 102, 241, 0.5);
    }

    .btn-primary:active {
        transform: translateY(0);
    }

    .btn-primary:disabled {
        opacity: 0.6;
        cursor: not-allowed;
        transform: none;
    }

    .alert {
        padding: 16px 20px;
        border-radius: var(--border-radius-sm);
        margin-bottom: 24px;
        display: flex;
        align-items: center;
        gap: 12px;
        animation: slideIn 0.3s ease-out;
        font-size: 14px;
        font-weight: 500;
    }

    .alert-danger {
        background: rgba(239, 68, 68, 0.1);
        color: var(--danger);
        border: 2px solid var(--danger);
    }

    .alert i {
        font-size: 20px;
    }

    .login-footer {
        text-align: center;
        margin-top: 30px;
        padding-top: 30px;
        border-top: 2px solid var(--gray-light);
        color: var(--gray);
        font-size: 14px;
    }

    .admin-badge {
        display: inline-flex;
        align-items: center;
        gap: 8px;
        background: linear-gradient(135deg, rgba(99, 102, 241, 0.1), rgba(139, 92, 246, 0.1));
        color: var(--primary);
        padding: 8px 16px;
        border-radius: 20px;
        font-size: 13px;
        font-weight: 600;
        margin-bottom: 20px;
    }

    @media (max-width: 576px) {
        .login-card {
            padding: 30px 20px;
        }

        .login-title {
            font-size: 26px;
        }
    }
</style>
@endsection

@section('content')
<div class="login-container">
    <div class="login-card">
        <div class="login-header">
            <div class="login-icon">
                <i class="fas fa-shield-halved"></i>
            </div>
            <div class="admin-badge">
                <i class="fas fa-crown"></i>
                Admin Access Only
            </div>
            <h1 class="login-title">School Management</h1>
            <p class="login-subtitle">Sign in to access the admin dashboard</p>
        </div>

        @if ($errors->any())
            <div class="alert alert-danger">
                <i class="fas fa-exclamation-circle"></i>
                <span>{{ $errors->first() }}</span>
            </div>
        @endif

        <form method="POST" action="{{ route('login.post') }}">
            @csrf
            
            <div class="form-group">
                <label class="form-label" for="email">
                    Email Address <span class="required">*</span>
                </label>
                <div class="input-group">
                    <i class="input-icon fas fa-envelope"></i>
                    <input 
                        type="email" 
                        id="email" 
                        name="email"
                        class="form-input @error('email') is-invalid @enderror" 
                        placeholder="admin@example.com"
                        value="{{ old('email') }}"
                        required
                        autofocus
                        autocomplete="email"
                    >
                </div>
                @error('email')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label class="form-label" for="password">
                    Password <span class="required">*</span>
                </label>
                <div class="input-group">
                    <i class="input-icon fas fa-lock"></i>
                    <input 
                        type="password" 
                        id="password" 
                        name="password"
                        class="form-input @error('password') is-invalid @enderror" 
                        placeholder="Enter your password"
                        required
                        autocomplete="current-password"
                    >
                </div>
                @error('password')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-check">
                <input 
                    type="checkbox" 
                    id="remember" 
                    name="remember"
                    class="form-check-input"
                >
                <label class="form-check-label" for="remember">
                    Remember me for 30 days
                </label>
            </div>

            <button type="submit" class="btn btn-primary">
                <i class="fas fa-sign-in-alt"></i>
                Sign In to Dashboard
            </button>
        </form>

        <div class="login-footer">
            <i class="fas fa-lock"></i>
            Secure Admin Portal &copy; {{ date('Y') }}
        </div>
    </div>
</div>
@endsection
