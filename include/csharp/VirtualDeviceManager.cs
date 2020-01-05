// DO NOT EDIT! This is an autogenerated file. All changes will be
// overwritten!

//	Copyright (c) 2016 Vidyo, Inc. All rights reserved.


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;
using System.Runtime.InteropServices;

namespace VidyoClient
{
	public class VirtualDeviceManager{
#if __IOS__
		const string importLib = "__Internal";
#else
		const string importLib = "libVidyoClient";
#endif
		private IntPtr objPtr; // opaque VidyoVirtualDeviceManager reference.
		public IntPtr GetObjectPtr(){
			return objPtr;
		}
		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		private static extern IntPtr VidyoVirtualDeviceManagerConstructCopyNative(IntPtr other);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		private static extern void VidyoVirtualDeviceManagerDestructNative(IntPtr obj);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		[return: MarshalAs(UnmanagedType.I1)]
		private static extern Boolean VidyoVirtualDeviceManagerLocalCameraEventNative(IntPtr m, IntPtr endpoint, IntPtr camera, [MarshalAs(UnmanagedType.I4)]Device.DeviceState state);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		[return: MarshalAs(UnmanagedType.I1)]
		private static extern Boolean VidyoVirtualDeviceManagerLocalMicrophoneEventNative(IntPtr m, IntPtr endpoint, IntPtr microphone, [MarshalAs(UnmanagedType.I4)]Device.DeviceState state);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		[return: MarshalAs(UnmanagedType.I1)]
		private static extern Boolean VidyoVirtualDeviceManagerLocalSpeakerEventNative(IntPtr m, IntPtr endpoint, IntPtr speaker, [MarshalAs(UnmanagedType.I4)]Device.DeviceState state);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		[return: MarshalAs(UnmanagedType.I1)]
		private static extern Boolean VidyoVirtualDeviceManagerMediaFailedEventNative(IntPtr m, IntPtr endpoint);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		public static extern IntPtr VidyoVirtualDeviceManagerGetUserDataNative(IntPtr obj);

		[DllImport(importLib, CallingConvention = CallingConvention.Cdecl)]
		public static extern void VidyoVirtualDeviceManagerSetUserDataNative(IntPtr obj, IntPtr userData);

		public VirtualDeviceManager(IntPtr other){
			objPtr = VidyoVirtualDeviceManagerConstructCopyNative(other);
			VidyoVirtualDeviceManagerSetUserDataNative(objPtr, GCHandle.ToIntPtr(GCHandle.Alloc(this, GCHandleType.Weak)));
		}
		~VirtualDeviceManager(){
			if(objPtr != IntPtr.Zero){
				VidyoVirtualDeviceManagerSetUserDataNative(objPtr, IntPtr.Zero);
				VidyoVirtualDeviceManagerDestructNative(objPtr);
			}
		}
		public Boolean LocalCameraEvent(Endpoint endpoint, LocalCamera camera, Device.DeviceState state){

			Boolean ret = VidyoVirtualDeviceManagerLocalCameraEventNative(objPtr, (endpoint != null) ? endpoint.GetObjectPtr():IntPtr.Zero, (camera != null) ? camera.GetObjectPtr():IntPtr.Zero, state);

			return ret;
		}
		public Boolean LocalMicrophoneEvent(Endpoint endpoint, LocalMicrophone microphone, Device.DeviceState state){

			Boolean ret = VidyoVirtualDeviceManagerLocalMicrophoneEventNative(objPtr, (endpoint != null) ? endpoint.GetObjectPtr():IntPtr.Zero, (microphone != null) ? microphone.GetObjectPtr():IntPtr.Zero, state);

			return ret;
		}
		public Boolean LocalSpeakerEvent(Endpoint endpoint, LocalSpeaker speaker, Device.DeviceState state){

			Boolean ret = VidyoVirtualDeviceManagerLocalSpeakerEventNative(objPtr, (endpoint != null) ? endpoint.GetObjectPtr():IntPtr.Zero, (speaker != null) ? speaker.GetObjectPtr():IntPtr.Zero, state);

			return ret;
		}
		public Boolean MediaFailedEvent(Endpoint endpoint){

			Boolean ret = VidyoVirtualDeviceManagerMediaFailedEventNative(objPtr, (endpoint != null) ? endpoint.GetObjectPtr():IntPtr.Zero);

			return ret;
		}
	};
}
